package com.study.platform.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.study.platform.dao.UserDao;
import com.study.platform.pojo.Role;
import com.study.platform.pojo.User;
import com.study.platform.pojo.UserRole;

@SuppressWarnings("unchecked")
@Component("userDao")
public class UserDaoImpl extends BaseDao implements UserDao {

	/**
	 * 根据Email地址获得User信息，用户登录
	 */
	@Override
	@Transactional(readOnly = true)
	public User findUserByEmail(String email) {
		Session session = getSession();
		User user = (User) session.createCriteria(User.class)
				.add(Restrictions.eq("email", email)).uniqueResult();

		if (user != null) {
			List<UserRole> roles = session.createCriteria(UserRole.class)
					.add(Restrictions.eq("userId", user.getId())).list();
			ArrayList<Integer> roleIds = new ArrayList<Integer>();
			for (UserRole role : roles) {
				roleIds.add(role.getRoleId());
			}

			List<Role> userRoles = session.createCriteria(Role.class)
					.add(Restrictions.in("id", roleIds.toArray())).list();
			for (Role role : userRoles) {
				Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) user
						.getAuthorities();
				if (authorities == null) {
					authorities = new ArrayList<GrantedAuthority>();
					user.setAuthorities(authorities);
				}

				authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			}
		}

		return user;
	}

	@Override
	public boolean save(User user, int[] roles) {

		Session session = getSession();
		Serializable id = session.save(user);

		for (int i : roles) {
			session.save(new UserRole((Long) id, i));
		}

		return true;

	}

	@Override
	public boolean checkEmailExist(String email) {
		Session session = getSession();
		long count = (Long) session.createCriteria(User.class).setProjection(Projections.rowCount()).add(Restrictions.eq("email", email)).uniqueResult();
		return count > 0;
	}

}
