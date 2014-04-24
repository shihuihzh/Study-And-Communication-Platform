package com.study.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.study.platform.dao.QuestionDao;
import com.study.platform.pojo.Question;

/**
 * 
 * 描述：问题dao实现
 * 
 * @ClassName: QuestionDaoImpl 
 * @author Fantasy
 * @date 2014年4月24日 下午8:53:49 
 * @version V1.0
 *
 */
@Repository
public class QuestionDaoImpl extends GenericDaoHibernate<Question, Integer> implements
		QuestionDao {

	public QuestionDaoImpl() {
		super(Question.class);
	}

	

	

}
