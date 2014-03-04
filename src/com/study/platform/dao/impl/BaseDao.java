package com.study.platform.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component("baseDao")
public class BaseDao {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
}
