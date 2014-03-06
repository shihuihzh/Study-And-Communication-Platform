package com.study.platform.dao;

import com.study.platform.pojo.UserEnableCheck;

public interface UserEnableCheckDao {

	/**
	 * 保存验证信息，准备邮箱验证
	 */
	void save(UserEnableCheck userEnableCheck);
	
	
	/**
	 * 验证成功过，删除验证
	 */
	void deleteByUUID(String UUID);
	
	/**
	 * 按UUID查找验证信息对象
	 */
	UserEnableCheck findByUUID(String UUID);
}
