package com.study.platform.service;

import com.study.platform.dto.UserFormDTO;

/**
 * 用户业务逻辑接口
 * @author fantasy
 *
 */
public interface UserService {

	/**
	 * 注册
	 * @param userFormDTO	用户信息
	 * @param roles			权限
	 * @return 
	 */
	int register(UserFormDTO userFormDTO, int[] roles);
	

	/**
	 * 检测Email时候已经存在
	 * @param email			email
	 * @return
	 */
	boolean checkEmailExist(String email);

}
