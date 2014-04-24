package com.study.platform.action;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ModelDriven;
import com.study.platform.dto.ApiUserFormDTO;
import com.study.platform.pojo.Group;
import com.study.platform.pojo.User;

@Service("apiAction")
@Scope("prototype")
public class ApiAction extends BaseAction implements ModelDriven<ApiUserFormDTO>, ServletRequestAware, ServletResponseAware {
	
	private ApiUserFormDTO apiUserFormDTO = new ApiUserFormDTO();
	private HttpServletRequest request;
	private JSONObject resultJson = new JSONObject();
	private HttpServletResponse response;
	private ServletContext application;
	
	
	/**
	 * 编辑器上传
	 */
	private File upfile;
	
	

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
	
	/**
	 * 编辑器图片上传
	 * @return
	 * @throws Exception
	 */
	public String editorUpload() throws Exception {

		String path = ImageUtil.saveImage(upfile, application.getRealPath("/"), false, 0, 0);
		//String result = "{\"name\":\""+ upfile +"\", \"originalName\": \""+ up.getOriginalName() +"\", \"size\": "+ up.getSize() +", \"state\": \""+ up.getState() +"\", \"type\": \""+ up.getType() +"\", \"url\": \""+ up.getUrl() +"\"}";
		resultJson = new JSONObject();
		resultJson.accumulate("originalName", "");
		resultJson.accumulate("size", new File(application.getRealPath(path)).length());
		resultJson.accumulate("type", ".png");
		resultJson.accumulate("url", request.getAttribute("basePath") + path);
		resultJson.accumulate("state", "SUCCESS");
		resultJson.accumulate("name", path.substring(path.lastIndexOf('/') +1));
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(resultJson.toString());
		
		return null;
	}
	
	/**
	 * 
	 * 描述：群组api
	 * 
	 * @Title: showGroup 
	 * @return
	 * @throws Exception
	 */
	public String groupApi() throws Exception {
		
		User userDetails = null;
		int status = 0;
		
		try {
			userDetails = (User) SecurityContextHolder.getContext()
				    .getAuthentication()
				    .getPrincipal();
		} catch (Exception e) {
			System.out.println("未登录！");
			status = 1;
		}
		
		String toDo = request.getParameter("do");
		if("show".equals(toDo)) {
			String id = request.getParameter("id");
			Group group = groupService.get(Integer.valueOf(id));
			Document doc = Jsoup.parse(group.getGroupDesc());
			String text = doc.body().text();
			String subText = text.substring(0, text.length() > 100 ? 100: text.length());
			if(text.length() > 100) {
				subText += "...";
			}
			// {"status":0,"data":{"id":"1040000000089449","isFollowed":false,"name":"java","followersWord":752,"isEdited":true,"url":"http:\/\/segmentfault.com\/t\/java","editUrl":"http:\/\/segmentfault.com\/t\/java\/edit","excerpt":"Java\u662f\u4e00\u79cd\u53ef\u4ee5\u64b0\u5199\u8de8\u5e73\u53f0\u5e94\u7528\u8f6f\u4ef6\u7684\u9762\u5411\u5bf9\u8c61\u7684\u7a0b\u5e8f\u8bbe\u8ba1\u8bed\u8a00\uff0c\u662f\u7531Sun Microsystems\u516c\u53f8\u4e8e1995\u5e745\u6708\u63a8\u51fa...","iconUrl":"http:\/\/sfault-avatar.b0.upaiyun.com\/354\/807\/3548072706-i-1040000000089449_icon"}}
			resultJson.accumulate("status", 0);
			
			JSONObject data = new JSONObject();
			data.accumulate("id", group.getGroupId());
			data.accumulate("isFollowed", groupService.isFollowed(group.getGroupId(), userDetails));
			data.accumulate("name", group.getGroupName());
			data.accumulate("followersWord", Long.valueOf(group.getGroupInterest()));
			data.accumulate("isEdited", true);
			data.accumulate("editUrl", "");
			data.accumulate("url", request.getAttribute("basePath") + "group/show?type=main&id=" + group.getGroupId());
			data.accumulate("excerpt", subText);
			data.accumulate("iconUrl", request.getAttribute("basePath") + group.getGroupSmallLogo());
			
			resultJson.accumulate("data", data.toString());
			
		} else if("follow".equals(toDo)) {
			
			if(status == 1) {
				resultJson.accumulate("status", status);
				return super.execute();
			}
			
			String id = request.getParameter("id");
			String cancel = request.getParameter("cancel");
			
			int groupId = Integer.valueOf(id);
			Group group = groupService.followOrCancel(groupId, userDetails.getId(), !"0".equals(cancel));
			
			resultJson.accumulate("status", 0);
			
			// {"status":0,"data":{"id":"1040000000089442","followers":603,"followersWord":603}}
			JSONObject data = new JSONObject();
			data.accumulate("id", group.getGroupId());
			data.accumulate("followers", group.getGroupInterest());
			data.accumulate("followersWord", group.getGroupInterest());
			
			resultJson.accumulate("data", data);
			
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
		this.application = request.getSession().getServletContext();
		
	}

	public JSONObject getResultJson() {
		return resultJson;
	}

	public void setResultJson(JSONObject resultJson) {
		this.resultJson = resultJson;

	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public File getUpfile() {
		return upfile;
	}

	public void setUpfile(File upfile) {
		this.upfile = upfile;
	}

	public static void main(String[] args) {
		System.out.println(Long.valueOf(null));
	}
	
}
