package com.study.platform.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * EduWorkExp entity. 教育与工作经历
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "edu_work_exp", catalog = "study_communication")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class EduWorkExp implements java.io.Serializable {

	// Fields

	private Integer id;
	private Long userId;
	private Integer type;
	private String prefix;
	private String suffix;

	// Constructors

	/** default constructor */
	public EduWorkExp() {
	}

	/** full constructor */
	public EduWorkExp(Integer type, String prefix, String suffix) {
		this.type = type;
		this.prefix = prefix;
		this.suffix = suffix;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	@Column(name = "user_id", nullable = false)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "prefix", nullable = false)
	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Column(name = "suffix", nullable = false)
	public String getSuffix() {
		return this.suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}