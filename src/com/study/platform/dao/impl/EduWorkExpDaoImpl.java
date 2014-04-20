package com.study.platform.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.study.platform.dao.EduWorkExpDao;
import com.study.platform.pojo.EduWorkExp;

@SuppressWarnings("unchecked")
@Component("eduWorkExpDao")
public class EduWorkExpDaoImpl extends GenericDaoHibernate<EduWorkExp, Integer> implements EduWorkExpDao {

	public EduWorkExpDaoImpl() {
		super(EduWorkExp.class);
	}
	
	@Override
	public void addEduWorkExp(EduWorkExp eduWorkExp) {
		Session session = getSession();
		session.save(eduWorkExp);
	}
	
	@Override
	public List<EduWorkExp> listAllEduWorkExp(Long userId) {
		Session session = getSession();
		return session.createQuery("from EduWorkExp e where e.userId = :userId order by e.id desc")
				.setLong("userId", userId).setCacheable(true).list();
	}

	@Override
	public List<EduWorkExp> listAnyExp(Integer expType, Long userId) {
		Session session = getSession();
		Criteria cri = session.createCriteria(EduWorkExp.class)
				.add(Restrictions.eq("type", expType))
				.add(Restrictions.eq("userId", userId));
		return cri.setCacheable(true).list();
	}

	@Override
	public void removeAnyInfoByPagePos(int pos, int type, Long userId) {
		Session session = getSession();
		
		Criteria cri = session.createCriteria(EduWorkExp.class)
				.add(Restrictions.eq("type", type))
				.add(Restrictions.eq("userId", userId))
				.addOrder(Order.desc("id"))
				.setMaxResults(1).setFirstResult(pos);
		EduWorkExp ewe = (EduWorkExp) cri.uniqueResult();
		if(ewe != null) {
			session.delete(ewe);			
		}
		
	}

}
