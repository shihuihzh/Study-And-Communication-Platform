package com.study.platform.dao;

import com.study.platform.pojo.User;

public interface UserDao {

	/**
	 * 根据Email地址获得User信息，用户登录
	 */
	public User findUserByEmail(String email);

	
	/**
	 * 保存用户
	 * @param user	用户
	 * @param roles	权限
	 * @return 
	 */
	public boolean save(User user, int[] roles);


	/**
	 * 检测email存在
	 * @param email
	 * @return
	 */
	public boolean checkEmailExist(String email);

}
