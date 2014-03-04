package com.study.platform.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.study.platform.dto.UserFormDTO;
import com.study.platform.pojo.Role;
import com.study.platform.service.impl.MailEngine;

/**
 * 用户管理action
 * @author fantasy
 *
 */
@Service("userAction")
@Scope("prototype")
public class UserAction extends BaseAction implements ModelDriven<UserFormDTO> {


	private static final long serialVersionUID = -8163437152331512240L;
	private UserFormDTO userFormDTO = new UserFormDTO();
	


	@Override
	public String execute() throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		return super.execute();
	}
	
	/**
	 * 注册
	 * @return
	 * @throws Exception
	 */
	public String register() throws Exception {
		
		int result = userService.register(userFormDTO, new int[] {Role.ROLE_USER});
		if(result == 1) {
			Map map = new HashMap<String, String>();
			map.put("email", "[您输入的Email已经被使用]");
			ActionContext.getContext().getValueStack().set("errors", map);
			return super.input();
		}
		return super.execute();
	}

	@Override
	public UserFormDTO getModel() {
		return this.userFormDTO;
	}
}
