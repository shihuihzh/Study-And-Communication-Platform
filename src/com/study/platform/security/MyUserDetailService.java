package com.study.platform.security;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.study.platform.dao.UserDao;
import com.study.platform.pojo.User;

@Service("myUserDetailService")
public class MyUserDetailService implements UserDetailsService {

	@Resource(name = "userDao")
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		User user = null;
		user = userDao.findUserByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("User Not Found!");
		}
		return user;
	}

}