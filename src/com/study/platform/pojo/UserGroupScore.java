package com.study.platform.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * UserGroupScore entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_group_score", catalog = "study_communication")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class UserGroupScore implements java.io.Serializable {

	// Fields

	private Long groupScoreId;
	private Integer score;
	private Integer groupId;
	private Long userId;

	// Constructors

	/** default constructor */
	public UserGroupScore() {
	}

	/** full constructor */
	public UserGroupScore(Integer score, Integer groupId, Long userId) {
		this.score = score;
		this.groupId = groupId;
		this.userId = userId;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "group_score_id", unique = true, nullable = false)
	public Long getGroupScoreId() {
		return this.groupScoreId;
	}

	public void setGroupScoreId(Long groupScoreId) {
		this.groupScoreId = groupScoreId;
	}

	@Column(name = "score")
	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(name = "group_id")
	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@Column(name = "user_id")
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}