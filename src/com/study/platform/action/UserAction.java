package com.study.platform.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.study.platform.dto.UserFormDTO;
import com.study.platform.pojo.Role;

/**
 * 用户管理action
 * @author fantasy
 *
 */
@Service("userAction")
@Scope("prototype")
public class UserAction extends BaseAction implements ModelDriven<UserFormDTO>, ServletRequestAware {


	private static final long serialVersionUID = -8163437152331512240L;
	private UserFormDTO userFormDTO = new UserFormDTO();
	private HttpServletRequest request;
	


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
	
	/**
	 * 邮箱验证后链接登陆，注册后登陆
	 */
	public String registerCheck() throws Exception {
		String id = request.getParameter("id");
		String key = request.getParameter("key");
		
		int result = userService.activityUser(id, key);
		
		/*UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				"admin@admin.com", "123456");

		// request.getSession();

		try {
			token.setDetails(new WebAuthenticationDetails(request));
			Authentication authenticatedUser = authenticationManager.authenticate(token);

			SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
			request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
		} catch (AuthenticationException e) {
			System.out.println("Authentication failed: " + e.getMessage());
			return super.input();
		}*/

		return super.execute();
	}

	@Override
	public UserFormDTO getModel() {
		return this.userFormDTO;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
}
