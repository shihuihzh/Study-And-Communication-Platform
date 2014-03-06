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
import com.study.platform.dao.UserEnableResetCheckDao;
import com.study.platform.dto.UserFormDTO;
import com.study.platform.pojo.User;
import com.study.platform.pojo.UserEnableResetCheck;
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
	private UserEnableResetCheckDao userEnableCheckDao;
	
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
			
			UserEnableResetCheck uec = new UserEnableResetCheck();
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
		model.put("enableURL", createUrl(uuid, sign, "check_user_register_url"));
		mailEngine.sendMessage(message, "accountCreated.vm", model);
	}
	
	/**
	 * 生成验证url
	 * @param uuid
	 * @param sign
	 * @return
	 */
	private String createUrl(String uuid, String sign, String function) {
		Properties prop = new Properties();
		try {
			prop.load(UserServiceImpl.class.getClassLoader().getResourceAsStream("website.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String siteUrl = prop.getProperty("site_url");
		String checkUrl = prop.getProperty(function);
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
		UserEnableResetCheck uec = userEnableCheckDao.findByUUID(id);
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
	
	/**
	 * 验证签名
	 * @param uuid	uuid
	 * @param user	用户信息
	 * @param sign	签名
	 * @return
	 */
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

	@Transactional(readOnly = true)
	@Override
	public boolean resendActivityEmail(String email) {
		UserEnableResetCheck usercheck = userEnableCheckDao.findByEmail(email);
		if (usercheck != null) {
			User u = userDao.findUserByEmail(email);
			if(!u.getAccountEnabled()) {
				sendUserActivityEmail(u, usercheck.getCheckUuid(), usercheck.getCheckSign());
				return true;
			}
		}
		
		
		return false;
		
	}

	@Transactional
	@Override
	public boolean sendResetPassword(String email) {
		User user = userDao.findUserByEmail(email);
		String uuid = UUID.randomUUID().toString();
		String sign = signForCheck(uuid, user);
		long expiredTime = System.currentTimeMillis() + 24 * 3600 * 1000;
		
		UserEnableResetCheck uerc = new UserEnableResetCheck();
		uerc.setCheckEmail(user.getEmail());
		uerc.setCheckSign(sign);
		uerc.setCheckUuid(uuid);
		uerc.setCheckExpiredTime(expiredTime);
		userEnableCheckDao.save(uerc);
		
		sendResetPasswordEmail(user, uuid, sign);
		
		return true;
	}

	/**
	 * 发送密码重置邮件
	 * @param user	用户信息
	 * @param uuid	uuid
	 * @param sign	签名
	 */
	private void sendResetPasswordEmail(User user, String uuid, String sign) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(user.getEmail());
		message.setSubject("密码重置邮件");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("nickname", user.getNickname());
		model.put("applicationURL", createUrl(uuid, sign, "reset_user_password"));
		mailEngine.sendMessage(message, "passwordRecovery.vm", model);
	}

	@Transactional
	@Override
	public User checkResetPasswordToken(String id, String key) {
		UserEnableResetCheck uec = userEnableCheckDao.findByUUID(id);
		if (uec == null || uec.getCheckExpiredTime() < System.currentTimeMillis()) {
			if(uec != null) {
				userEnableCheckDao.deleteByUUID(id);				
			}
			return null;
		}
		
		String email = uec.getCheckEmail();		
		User user = userDao.findUserByEmail(email);
		boolean isValid = signValid(id, user, key);
		
		if(isValid) {
			return user;
		} else {
			return null;		
		}
	}

	
	@Transactional
	@Override
	public boolean resetPassword(User user, String password) {
		userDao.resetPassword(user, passwordEncoder.encode(password));
		return true;
	}

	

}
