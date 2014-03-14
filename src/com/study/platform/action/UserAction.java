package com.study.platform.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.study.platform.dto.UserFormDTO;
import com.study.platform.pojo.EduWorkExp;
import com.study.platform.pojo.Role;
import com.study.platform.pojo.User;
import com.study.platform.taglib.OnTopAlertTag;
import com.study.platform.util.WebSitePropUtil;

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
	private String settingTab;
	
	public String getSettingTab() {
		return settingTab;
	}

	public void setSettingTab(String settingTab) {
		this.settingTab = settingTab;
	}

	@Override
	public String execute() throws Exception {
		//SimpleMailMessage message = new SimpleMailMessage();
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
	 * 邮箱验证后激活账户或者转到重置密码页面
	 */
	public String emailCheck() throws Exception {
		String id = request.getParameter("cid");
		String key = request.getParameter("key");
		
		String actionName = ActionContext.getContext().getName();
		switch(actionName) {
		case "reset_password":
			User user = userService.checkResetPasswordToken(id, key);
			if(user != null) {
				request.getSession().setAttribute("user", user);
				return super.SUCCESS;
			} else {
				return super.input();
			}
			
		case "register_check":
			int result = userService.activityUser(id, key);
			
			if (result  == 0) {
				request.getSession().setAttribute("SPRING_SECURITY_SAVED_REQUEST", WebSitePropUtil.webSiteProp.getProperty("site_url") + WebSitePropUtil.webSiteProp.getProperty("user_setting"));
				return super.execute();
			} else {
				return super.input();
			}
		}
		
		return super.input();
		
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
	}
	
	
	
	/**
	 * 发送邮箱链接（激活邮件，重置密码邮件）
	 */
	public String sendEmail() throws Exception {
		String email = request.getParameter("email");
		String actionName = ActionContext.getContext().getName();
		request.setAttribute("email", email);
		boolean result = false;
		
		switch(actionName) {
		case "resend_email":
			result = userService.resendActivityEmail(email);
			if (result) {
				request.setAttribute("operationTitle", "重新发送激活");
			} 
			break;
			
		case "find_password_post":
			result = userService.sendResetPassword(email);
			if (result) {
				request.setAttribute("operationTitle", "重置密码");			
			} 
			break;
		}
		
		if(result) {
			return super.SUCCESS;
		}
		return super.input();
	
	}
	
	/**
	 * 重置新密码
	 */
	public String resetPassword() throws Exception {
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm_password");
		
		if(password.length() < 6) {
			request.setAttribute("error", "密码小于六位");
			return super.input();
		}
		
		if(!password.equals(confirmPassword)) {
			request.setAttribute("error", "两次密码不一致");
			return super.input();
		}
		
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			return "fail";
		}
		userService.resetPassword(user, password);
		
		return super.SUCCESS;
	}
	
	/**
	 * 设置页面路由
	 * @return
	 * @throws Exception
	 */
	public String settingsRouter() throws Exception {
		String method = request.getMethod();
		String tab = request.getParameter("tab");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(StringUtils.isEmpty(tab)) {
			tab = "base";
		}
		this.settingTab = tab;
		
		switch(tab) {
		case "base":
			request.setAttribute("user", user);
			if(!"get".equalsIgnoreCase(method)) {
				userFormDTO.setId(user.getId());
				User newUser = userService.updateUser(userFormDTO);
				request.getSession().setAttribute(OnTopAlertTag.ALERT_MSG, "修改成功了！");
				BeanUtils.copyProperties(user, newUser);
			}
			break;
			
		case "record":
			List<EduWorkExp> exps = userService.getExpsByUserId(user.getId());
			request.setAttribute("exps", exps);
			break;
			
		case "auth":
			if(!"get".equalsIgnoreCase(method)) {
				String oldPassword = request.getParameter("old_password").trim();
				String password = request.getParameter("password").trim();
				String confirmPassword = request.getParameter("confirm_password").trim();
				
				if(!checkChangePassword(user, oldPassword, password, confirmPassword)) {
					return super.input();
				}
				
				String encryptPassword =  userService.resetPassword(user, password);
				user.setPassword(encryptPassword);
				request.getSession().setAttribute(OnTopAlertTag.ALERT_MSG, "修改密码成功了！");
			}
			break;
		default:
			this.settingTab = "base";
			request.setAttribute("user", user);
			break;
		}
		
		request.setAttribute("tab", this.settingTab);
		return super.execute();
	}

	/**
	 * 修改密码检查
	 * @param user
	 * @param oldPassword
	 * @param password
	 * @param confirmPassword
	 * @return
	 */
	private boolean checkChangePassword(User user, String oldPassword,
			String password, String confirmPassword) {
		boolean isSuccess = true;
		boolean matches = passwordEncoder.matches(oldPassword, user.getPassword());
		if(!matches) {
			isSuccess = false;
			request.setAttribute("error_wrong", "原密码错误！");
		}
		
		if(password.length() < 6) {
			isSuccess = false;
			request.setAttribute("error_toShort", "密码必须大于6位！");
		}
		
		if(!password.equals(confirmPassword)) {
			isSuccess = false;
			request.setAttribute("error_notSame", "两次密码不一致");
		}
		
		return isSuccess;
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
