package com.study.platform.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Indexed;

/**
 * Question entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "question", catalog = "study_communication")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Question implements java.io.Serializable {

	// Fields

	private Integer questionId;
	private String questionContent;
	private Integer questionAnswerCount;
	private Long questionCreateTime;
	private Integer questionGreat;
	private Integer questionCriticize;
	private Long questionUserId;

	// Constructors

	/** default constructor */
	public Question() {
	}

	/** full constructor */
	public Question(String questionContent, Integer questionAnswerCount,
			Long questionCreateTime, Integer questionGreat,
			Integer questionCriticize, Long questionUserId) {
		this.questionContent = questionContent;
		this.questionAnswerCount = questionAnswerCount;
		this.questionCreateTime = questionCreateTime;
		this.questionGreat = questionGreat;
		this.questionCriticize = questionCriticize;
		this.questionUserId = questionUserId;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "question_id", unique = true, nullable = false)
	public Integer getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	@Column(name = "question_content", length = 2048)
	public String getQuestionContent() {
		return this.questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	@Column(name = "question_answer_count")
	public Integer getQuestionAnswerCount() {
		return this.questionAnswerCount;
	}

	public void setQuestionAnswerCount(Integer questionAnswerCount) {
		this.questionAnswerCount = questionAnswerCount;
	}

	@Column(name = "question_create_time")
	public Long getQuestionCreateTime() {
		return this.questionCreateTime;
	}

	public void setQuestionCreateTime(Long questionCreateTime) {
		this.questionCreateTime = questionCreateTime;
	}

	@Column(name = "question_great")
	public Integer getQuestionGreat() {
		return this.questionGreat;
	}

	public void setQuestionGreat(Integer questionGreat) {
		this.questionGreat = questionGreat;
	}

	@Column(name = "question_criticize")
	public Integer getQuestionCriticize() {
		return this.questionCriticize;
	}

	public void setQuestionCriticize(Integer questionCriticize) {
		this.questionCriticize = questionCriticize;
	}

	@Column(name = "question_user_id")
	public Long getQuestionUserId() {
		return this.questionUserId;
	}

	public void setQuestionUserId(Long questionUserId) {
		this.questionUserId = questionUserId;
	}

}