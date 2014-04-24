package com.study.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.platform.dao.GroupFollowDao;
import com.study.platform.dto.GroupVo;
import com.study.platform.pojo.Group;
import com.study.platform.pojo.GroupFollow;
import com.study.platform.pojo.User;
import com.study.platform.service.GroupService;
import com.study.platform.service.QuestionService;
import com.study.platform.util.PageController;

@Service("groupService")
@Transactional
public class GroupServiceImpl extends GenericServiceImpl<Group, Integer> implements
		GroupService {
	
	@Resource
	private QuestionService questionService;
	
	@Resource
	private GroupFollowDao groupFollowDao;

	@Override
	public GroupVo getGroupInfoById(Integer id) {
		Group group = dao.get(id);
		
		GroupVo gv = new GroupVo();
		gv.setGroup(group);
		return gv;
	}

	@Override
	public GroupVo getGroupAllById(Integer id, User user, PageController questionPc, Map term, String orderBy) {

		
		GroupVo gv = this.getGroupInfoById(id);
		Group group = gv.getGroup();
		
		gv.setQuestion(questionService.findByProp(questionPc, term, orderBy));
		gv.setFollowed(this.isFollowed(id, user));
		return gv;
	}

	@Override
	public Group followOrCancel(int groupId, long id, boolean cancel) {
		Map term = new HashMap();
		term.put("groupId", groupId);
		term.put("groupUserId", (int)id);
		
		Group group = dao.get(groupId);
		List<GroupFollow> follows = groupFollowDao.findByProp(null, term, null);
		
		if(cancel && !follows.isEmpty()) {
			for(GroupFollow f : follows) {
				groupFollowDao.remove(f);
				group.setGroupInterest(group.getGroupInterest()-1);
			}

		} else if(!cancel && follows.isEmpty()) {			
			GroupFollow follow = new GroupFollow();
			follow.setGroupId(groupId);
			follow.setGroupUserId((int)id);
			groupFollowDao.save(follow);
			group.setGroupInterest(group.getGroupInterest()+1);
		}
		return group;
	}

	@Override
	public boolean isFollowed(Integer groupId, User userDetails) {
		if(userDetails == null) {
			return false;
		}
		
		Map term = new HashMap();
		term.put("groupId", groupId);
		term.put("groupUserId", (int)(long)userDetails.getId());
		List<GroupFollow> follows = groupFollowDao.findByProp(null, term, null);
		
		return !follows.isEmpty();
		
	}

	
}
