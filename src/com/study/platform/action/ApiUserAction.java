package com.study.platform.action;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ModelDriven;
import com.study.platform.dto.ApiUserFormDTO;
import com.study.platform.pojo.User;

@Service("apiUserAction")
@Scope("prototype")
public class ApiUserAction extends BaseAction implements ModelDriven<ApiUserFormDTO>, ServletRequestAware {
	
	private ApiUserFormDTO apiUserFormDTO = new ApiUserFormDTO();
	private HttpServletRequest request;
	private JSONObject resultJson = new JSONObject();

	@Override
	public String execute() throws Exception {
		
		String toDo = request.getParameter("do");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		switch(toDo) {
		case "addInfo":
			if(StringUtils.isEmpty(apiUserFormDTO.getPrefix())) {
				resultJson.element("status", 4);
				break;
			}
			
			if(StringUtils.isEmpty(apiUserFormDTO.getSuffix())) {
				resultJson.element("status", 7);
				break;
			}
			
			userService.addInfo(apiUserFormDTO, user.getId());
			
			// 成功 {"status":0,"data":null}
			resultJson.element("status", 0);
			resultJson.put("data", null);
			break;
		case "removeInfo":
			userService.removeInfo(apiUserFormDTO, user.getId());
			// 成功 {"status":0,"data":null}
			resultJson.element("status", 0);
			resultJson.put("data", null);
		default:
			
		}
		
		
		
		
		return super.execute();
	}
	
	

	@Override
	public ApiUserFormDTO getModel() {
		return apiUserFormDTO;
	}



	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request= request;
		
	}

	public JSONObject getResultJson() {
		return resultJson;
	}

	public void setResultJson(JSONObject resultJson) {
		this.resultJson = resultJson;
	}
		
}
