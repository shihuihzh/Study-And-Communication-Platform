package com.study.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.study.platform.dao.GroupDao;
import com.study.platform.pojo.Group;

@Repository("groupDao")
public class GroupDaoImpl extends GenericDaoHibernate<Group, Integer> implements
		GroupDao {

	public GroupDaoImpl() {
		super(Group.class);
	}

	

}
