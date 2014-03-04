package com.study.platform.dao;

import com.study.platform.pojo.User;

public interface UserDao {

	/**
	 * 根据Email地址获得User信息，用户登录
	 */
	public User findUserByEmail(String email);
}
