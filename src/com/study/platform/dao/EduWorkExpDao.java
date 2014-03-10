package com.study.platform.dao;

import java.util.List;

import com.study.platform.pojo.EduWorkExp;

/**
 * 教育工作经历dao接口
 * @author fantasy
 *
 */
public interface EduWorkExpDao {

	/**
	 * 增加经历
	 * @param eduWorkExp
	 */
	void addEduWorkExp(EduWorkExp eduWorkExp);
	
	/**
	 * 列出所有经历
	 * @param userId 	用户id
	 * @return 		返回经历集合
	 */
	List<EduWorkExp> listAllEduWorkExp(Long userId);
	
	
	/**
	 * 列出某种经历
	 * @param userId 	用户id
	 * @return 返回经历集合
	 */
	List<EduWorkExp> listAnyExp(Integer expType, Long userId);

	/**
	 * 删除某一条经历
	 * @param pos	页面显示的位置
	 * @param type	类型
	 * @param userId	用户id
	 */
	void removeAnyInfoByPagePos(int pos, int type, Long userId);
	
}
