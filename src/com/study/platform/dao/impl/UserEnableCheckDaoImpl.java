package com.study.platform.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.study.platform.dao.UserEnableCheckDao;
import com.study.platform.pojo.UserEnableCheck;

@Component("userEnableCheckDao")
public class UserEnableCheckDaoImpl extends BaseDao implements UserEnableCheckDao {

	@Override
	public void save(UserEnableCheck userEnableCheck) {
		Session session = getSession();
		session.save(userEnableCheck);
		
	}

	@Override
	public void deleteByUUID(String UUID) {
		Session session = getSession();
		Query hql = session.createQuery("delete from UserEnableCheck uec where uec.checkUuid = :uuid");
		hql.setString("uuid", UUID);
		hql.executeUpdate();
	}

	@Override
	public UserEnableCheck findByUUID(String UUID) {
		Session session = getSession();
		Criteria cri = session.createCriteria(UserEnableCheck.class).add(Restrictions.eq("checkUuid", UUID));
		return (UserEnableCheck) cri.uniqueResult();
	}
	
	

}
