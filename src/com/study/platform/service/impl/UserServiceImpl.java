package com.study.platform.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.study.platform.dao.UserDao;
import com.study.platform.dao.UserEnableCheckDao;
import com.study.platform.dto.UserFormDTO;
import com.study.platform.pojo.User;
import com.study.platform.pojo.UserEnableCheck;
import com.study.platform.service.UserService;

/**
 * 用户业务逻辑实现类
 * @author fantasy
 *
 */

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource(name="passwordEncoder")
	private PasswordEncoder passwordEncoder;
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="userEnableCheckDao")
	private UserEnableCheckDao userEnableCheckDao;
	
	@Resource
	private MailEngine mailEngine;


	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int register(UserFormDTO userFormDTO, int[] roles) {
		
		if (!checkEmailExist(userFormDTO.getEmail())) {			
			User user = new User(userFormDTO.getEmail(), userFormDTO.getNickname(), passwordEncoder.encode(userFormDTO.getPassword()));
			
			userDao.save(user, roles);

			String uuid = UUID.randomUUID().toString();
			String sign = signForCheck(uuid, user);
			
			UserEnableCheck uec = new UserEnableCheck();
			uec.setCheckEmail(user.getEmail());
			uec.setCheckSign(sign);
			uec.setCheckUuid(uuid);
			userEnableCheckDao.save(uec);
			
			sendUserActivityEmail(user, uuid, sign);
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * 发送激活邮件
	 * @param user	用户信息
	 * @param uuid	验证唯uuid
	 * @param sign	签名
	 */
	private void sendUserActivityEmail(User user, String uuid, String sign) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(user.getEmail());
		message.setSubject("欢迎加入学习交流社区");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("message", "欢迎您的加入！");
		model.put("nickname", user.getNickname());
		model.put("enableURL", createUrl(uuid, sign));
		mailEngine.sendMessage(message, "accountCreated.vm", model);
	}
	
	/**
	 * 生成验证url
	 * @param uuid
	 * @param sign
	 * @return
	 */
	private String createUrl(String uuid, String sign) {
		Properties prop = new Properties();
		try {
			prop.load(UserServiceImpl.class.getClassLoader().getResourceAsStream("website.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String siteUrl = prop.getProperty("site_url");
		String checkUrl = prop.getProperty("check_user_register_url");
		StringBuilder sb = new StringBuilder();
		sb.append(siteUrl);
		sb.append(checkUrl);
		sb.append("?id=");
		sb.append(uuid);
		sb.append("&key=");
		sb.append(sign);
		System.out.println(sb.toString());
		return sb.toString();
	}

	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	@Override
	public boolean checkEmailExist(String email) {
		return userDao.checkEmailExist(email);
	}
	
	@Transactional
	@Override
	public int activityUser(String id, String key) {
		UserEnableCheck uec = userEnableCheckDao.findByUUID(id);
		if(uec == null) {
			return 1;
		}
		
		String email = uec.getCheckEmail();		
		User user = userDao.findUserByEmail(email);
		boolean isValid = signValid(id, user, key);
		
		if(isValid) {
			user.setAccountEnabled(true);
			userEnableCheckDao.deleteByUUID(id);
			return 0;
		} else {
			return 2;		
		}
		
	}
	
	
	private boolean signValid(String uuid, User user, String sign) {
		StringBuilder sb = new StringBuilder();	
		sb.append(uuid);
		sb.append(",");
		sb.append(user.getEmail());
		
		return passwordEncoder.matches(sb.toString(), sign);
	}

	/**
	 * 生成验证签名
	 * @param uuid
	 * @param user
	 */
	private String signForCheck(String uuid, User user) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(uuid);
		sb.append(",");
		sb.append(user.getEmail());
		
		String sign = passwordEncoder.encode(sb);
		return sign;
	}

	@Override
	public boolean resendActivityEmail(String email) {
		UserEnableCheck usercheck = userEnableCheckDao.findByEmail(email);
		if (usercheck != null) {
			User u = userDao.findUserByEmail(email);
			if(!u.getAccountEnabled()) {
				sendUserActivityEmail(u, usercheck.getCheckUuid(), usercheck.getCheckSign());
				return true;
			}
		}
		
		
		return false;
		
	}

	

}
