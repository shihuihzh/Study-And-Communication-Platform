package com.study.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.study.platform.dao.GroupFollowDao;
import com.study.platform.pojo.GroupFollow;

@Repository("groupFollowDao")
public class GroupFollowDaoImpl extends GenericDaoHibernate<GroupFollow, Integer> implements
		GroupFollowDao {

	public GroupFollowDaoImpl() {
		super(GroupFollow.class);
	}

	

}
