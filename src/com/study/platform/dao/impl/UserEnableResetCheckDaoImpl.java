package com.study.platform.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.study.platform.dao.UserEnableResetCheckDao;
import com.study.platform.pojo.UserEnableResetCheck;

@Component("userEnableCheckDao")
public class UserEnableResetCheckDaoImpl extends BaseDao implements UserEnableResetCheckDao {

	@Override
	public void save(UserEnableResetCheck userEnableCheck) {
		Session session = getSession();
		session.save(userEnableCheck);
		
	}

	@Override
	public void deleteByUUID(String UUID) {
		Session session = getSession();
		Query hql = session.createQuery("delete from UserEnableResetCheck uec where uec.checkUuid = :uuid");
		hql.setString("uuid", UUID);
		hql.executeUpdate();
	}

	@Override
	public UserEnableResetCheck findByUUID(String UUID) {
		Session session = getSession();
		Criteria cri = session.createCriteria(UserEnableResetCheck.class).add(Restrictions.eq("checkUuid", UUID));
		return (UserEnableResetCheck) cri.uniqueResult();
	}

	@Override
	public UserEnableResetCheck findByEmail(String email) {
		Session session = getSession();
		Criteria cri = session.createCriteria(UserEnableResetCheck.class).add(Restrictions.eq("checkEmail", email));
		return (UserEnableResetCheck) cri.uniqueResult();
	}
	
	

}
