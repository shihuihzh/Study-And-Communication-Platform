package com.study.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.study.platform.dao.AnswerDao;
import com.study.platform.dao.QuestionDao;
import com.study.platform.pojo.Answer;
import com.study.platform.pojo.Question;

/**
 * 
 * 描述：答案dao实现
 * 
 * @ClassName: AnswerDaoImpl 
 * @author Fantasy
 * @date 2014年4月24日 下午8:53:49 
 * @version V1.0
 *
 */
@Repository
public class AnswerDaoImpl extends GenericDaoHibernate<Answer, Integer> implements
		AnswerDao {

	public AnswerDaoImpl() {
		super(Answer.class);
	}

}
