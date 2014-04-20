package com.study.platform.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Answer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "answer", catalog = "study_communication")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Answer implements java.io.Serializable {

	// Fields

	private Integer answerId;
	private Integer questionId;
	private String answerContent;
	private Integer answerCriticize;
	private Integer answerGreat;
	private Long answerCreateTime;
	private Long answerUserId;

	// Constructors

	/** default constructor */
	public Answer() {
	}

	/** full constructor */
	public Answer(Integer questionId, String answerContent,
			Integer answerCriticize, Integer answerGreat,
			Long answerCreateTime, Long answerUserId) {
		this.questionId = questionId;
		this.answerContent = answerContent;
		this.answerCriticize = answerCriticize;
		this.answerGreat = answerGreat;
		this.answerCreateTime = answerCreateTime;
		this.answerUserId = answerUserId;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "answer_id", unique = true, nullable = false)
	public Integer getAnswerId() {
		return this.answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	@Column(name = "question_id")
	public Integer getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	@Column(name = "answer_content", length = 2048)
	public String getAnswerContent() {
		return this.answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	@Column(name = "answer_criticize")
	public Integer getAnswerCriticize() {
		return this.answerCriticize;
	}

	public void setAnswerCriticize(Integer answerCriticize) {
		this.answerCriticize = answerCriticize;
	}

	@Column(name = "answer_great")
	public Integer getAnswerGreat() {
		return this.answerGreat;
	}

	public void setAnswerGreat(Integer answerGreat) {
		this.answerGreat = answerGreat;
	}

	@Column(name = "answer_create_time")
	public Long getAnswerCreateTime() {
		return this.answerCreateTime;
	}

	public void setAnswerCreateTime(Long answerCreateTime) {
		this.answerCreateTime = answerCreateTime;
	}

	@Column(name = "answer_user_id")
	public Long getAnswerUserId() {
		return this.answerUserId;
	}

	public void setAnswerUserId(Long answerUserId) {
		this.answerUserId = answerUserId;
	}

}