package com.study.platform.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionSupport;
import com.study.platform.service.GroupService;
import com.study.platform.service.UserService;

@Service("baseAction")
public class BaseAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected HttpServletRequest request;
	
	@Resource(name="userService")
	protected UserService userService;
	
	@Resource(name="groupService")
	protected GroupService groupService;
	
	@Resource
	protected AuthenticationManager authenticationManager;
	
	@Resource(name = "passwordEncoder")
	protected PasswordEncoder passwordEncoder;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

}
