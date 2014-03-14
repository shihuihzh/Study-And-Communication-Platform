package com.study.platform.action;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionSupport;
import com.study.platform.service.UserService;

@Service("baseAction")
public class BaseAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource(name="userService")
	protected UserService userService;
	
	@Resource
	protected AuthenticationManager authenticationManager;
	
	@Resource(name = "passwordEncoder")
	protected PasswordEncoder passwordEncoder;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

}
