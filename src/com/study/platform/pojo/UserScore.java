package com.study.platform.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * UserScore entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_score", catalog = "study_communication")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class UserScore implements java.io.Serializable {

	// Fields

	private Long scoreId;
	private Integer popularity;
	private Integer admire;

	// Constructors

	/** default constructor */
	public UserScore() {
	}

	/** full constructor */
	public UserScore(Integer popularity, Integer admire) {
		this.popularity = popularity;
		this.admire = admire;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "score_id", unique = true, nullable = false)
	public Long getScoreId() {
		return this.scoreId;
	}

	public void setScoreId(Long scoreId) {
		this.scoreId = scoreId;
	}

	@Column(name = "popularity")
	public Integer getPopularity() {
		return this.popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}

	@Column(name = "admire")
	public Integer getAdmire() {
		return this.admire;
	}

	public void setAdmire(Integer admire) {
		this.admire = admire;
	}

}