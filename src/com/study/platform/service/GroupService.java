package com.study.platform.service;

import java.util.Map;

import com.study.platform.dto.GroupVo;
import com.study.platform.pojo.Group;
import com.study.platform.pojo.User;
import com.study.platform.util.PageController;

public interface GroupService extends GenericService<Group, Integer> {

	
	/**
	 * 
	 * 描述：群组信息
	 * 
	 * @Title: getGroupInfoById 
	 * @param id
	 * @return
	 */
	GroupVo getGroupInfoById(Integer id);

	/**
	 * 
	 * 描述：群组信息所有
	 * 
	 * @Title: getGroupAllById 
	 * @param id
	 * @param questionPc	问题分页控制器
	 * @param term	条件
	 * @return
	 */
	GroupVo getGroupAllById(Integer id, User user, PageController questionPc,
			Map term, String orderBy);

	/**
	 * 
	 * 描述：关注或取消关注
	 * 
	 * @Title: followOrCancel 
	 * @param groupId
	 * @param id
	 * @return
	 */

	Group followOrCancel(int groupId, long id, boolean cancel);

	/**
	 * 
	 * 描述：判断某人时候关注
	 * 
	 * @Title: isFollowed 
	 * @param groupId
	 * @param userDetails
	 * @return
	 */
	boolean isFollowed(Integer groupId, User userDetails);

	

}
