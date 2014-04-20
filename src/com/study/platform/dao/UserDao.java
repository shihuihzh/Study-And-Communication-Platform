package com.study.platform.dao;

import com.study.platform.pojo.User;

public interface UserDao extends GenericDao<User, Long> {

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


	/**
	 * 重置密码
	 * @param user
	 * @param password
	 */
	public void resetPassword(User user, String password);


	/**
	 * 修改用户
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * 通过id找User
	 * @param id	user id
	 * @return
	 */

	public User finUserById(Long id);

}
