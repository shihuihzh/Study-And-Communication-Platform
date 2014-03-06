package com.study.platform.service;

import com.study.platform.dto.UserFormDTO;
import com.study.platform.pojo.User;

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


	/**
	 * 激活用户
	 * @param id	uuid
	 * @param key	签名
	 * @return	0：成功 
	 * 			1：不存在激活
	 * 			2：验证失败
	 */
	int activityUser(String id, String key);


	/**
	 * 重新发送激活邮箱验证
	 * @param email	邮箱
	 * @return 
	 */
	boolean resendActivityEmail(String email);


	/**
	 * 发送重置密码连接
	 * @param email 邮箱
	 * @return
	 */
	boolean sendResetPassword(String email);

	/**
	 * 验证找回密码连接是否有效
	 * @param id	uuid
	 * @param key	签名
	 * @return
	 */

	User checkResetPasswordToken(String id, String key);


	/**
	 * 修改密码
	 * @param user		用户
	 * @param password	新密码
	 * @return
	 */
	boolean resetPassword(User user, String password);

}
