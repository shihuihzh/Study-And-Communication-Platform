package com.study.platform.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ModelDriven;
import com.study.platform.dto.GroupDTO;
import com.study.platform.dto.GroupVo;
import com.study.platform.pojo.Group;
import com.study.platform.pojo.User;
import com.study.platform.util.PageController;

/**
 * 
 * 描述：群组acion
 * 
 * @ClassName: GroupAction 
 * @author Fantasy
 * @date 2014年4月20日 上午11:17:19 
 * @version V1.0
 *
 */
@Service("groupAction")
@Scope("prototype")
public class GroupAction extends BaseAction implements ModelDriven<GroupDTO> {

	
	private GroupDTO dto = new GroupDTO();
	private ServletContext application;
	private HttpSession session;
	
	/**
	 * 
	 * 描述：列出所有群组
	 * 
	 * @Title: listGroup 
	 * @return
	 * @throws Exception
	 */
	public String listGroup() throws Exception {
		List<Group> groups = groupService.getAll();
		request.setAttribute("groups", groups);
		return super.execute();
	}
	
	/**
	 * 
	 * 描述：添加群组
	 * 
	 * @Title: addGroup 
	 * @return
	 * @throws Exception
	 */
	public String addGroup() throws Exception {
		
		String path = ImageUtil.saveForceImage(dto.getGroupLogo(), application.getRealPath("/"), true, 100, 100);
		String spath = ImageUtil.saveForceImage(dto.getGroupLogo(), application.getRealPath("/"), true, 16, 16);
		
		User userDetails = (User) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		
		Group g = new Group();
		g.setGroupCreatorId((int)(long)userDetails.getId());
		g.setGroupDesc(dto.getGroupDesc());
		g.setGroupLogo(path);
		g.setGroupSmallLogo(spath);
		g.setGroupName(dto.getGroupName());
		g.setGroupInterest(0l);
		
		groupService.save(g);
		return super.execute();
	}
	
	/**
	 * 
	 * 描述：查看群组
	 * 
	 * @Title: showGroup 
	 * @return
	 * @throws Exception
	 */
	public String showGroup() throws Exception {
		
		User userDetails = null;
		try {
			userDetails = (User) SecurityContextHolder.getContext()
				    .getAuthentication()
				    .getPrincipal();
		} catch (Exception e1) {
			System.out.println("未登录");
		}
		
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		if("info".equals(type)) {
			GroupVo groupVo = groupService.getGroupInfoById(Integer.valueOf(id));
			request.setAttribute("groupVo", groupVo);
			return "info";
		} else {
			int page = 1;
			try {
				page = Integer.valueOf(request.getParameter("page"));
			} catch (Exception e) {
				System.out.println("页数重置1");
			}
			
			PageController questPC = (PageController) session.getAttribute("questPC");
			if(questPC == null) {
				questPC = new PageController(10);
				session.setAttribute("questPC", questPC);
			}
			
			Map map = new HashMap();
			
			String orderBy =  "questionCreateTime";
			String searche =  request.getParameter("search");
			String order =  request.getParameter("order");
			
			if(!StringUtils.isEmpty(order)) {
				if("votes".equals(order)) {
					orderBy = "questionGreat";
				} else if("accepted".equals(order)) {
					map.put("accepted", true);
				} else if("unanswered".equals(order)) {
					map.put("questionAnswerCount", 0);
				}
			}
			
			
			
			
			map.put("questionTitle", StringUtils.isEmpty(searche)?"":searche);
			
			GroupVo groupVo = groupService.getGroupAllById(Integer.valueOf(id),userDetails,questPC, map, orderBy);
			request.setAttribute("groupVo", groupVo);
			return "main";
		}
		
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
		this.application = request.getSession().getServletContext();
		
	}

	@Override
	public GroupDTO getModel() {
		return this.dto ;
	}
}
