package com.study.platform.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.study.platform.dao.UserDao;
import com.study.platform.dto.UserFormDTO;
import com.study.platform.pojo.User;
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
	
	@Resource
	private MailEngine mailEngine;


	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int register(UserFormDTO userFormDTO, int[] roles) {
		
		if (!checkEmailExist(userFormDTO.getEmail())) {			
			User user = new User(userFormDTO.getEmail(), userFormDTO.getNickname(), passwordEncoder.encode(userFormDTO.getPassword()));
			
			userDao.save(user, roles);
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(user.getEmail());
			message.setSubject("欢迎加入学习交流社区");
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("message", "欢迎您的加入！");
			model.put("nickname", user.getNickname());
			model.put("enableURL", "http://www.baidu.com");
			mailEngine.sendMessage(message, "accountCreated.vm", model);
			return 0;
		} else {
			return 1;
		}
	}
	
	@Transactional(readOnly = true)
	@Override
	public boolean checkEmailExist(String email) {
		return userDao.checkEmailExist(email);
	}
	

}
