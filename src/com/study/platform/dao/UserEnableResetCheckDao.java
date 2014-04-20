package com.study.platform.dao;

import com.study.platform.pojo.UserEnableResetCheck;

public interface UserEnableResetCheckDao extends GenericDao<UserEnableResetCheck, Integer> {

	
	
	/**
	 * 验证成功过，删除验证
	 */
	void deleteByUUID(String UUID);
	
	/**
	 * 按UUID查找验证信息对象
	 */
	UserEnableResetCheck findByUUID(String UUID);


	/**
	 * 按email查找验证信息对象
	 * @param email
	 */
	UserEnableResetCheck findByEmail(String email);
}
