package com.study.platform.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * GroupFollow entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "group_follow", catalog = "study_communication")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class GroupFollow implements java.io.Serializable {

	// Fields

	private Integer groupFollowId;
	private Integer groupId;
	private Integer groupUserId;

	// Constructors

	/** default constructor */
	public GroupFollow() {
	}

	/** full constructor */
	public GroupFollow(Integer groupId, Integer groupUserId) {
		this.groupId = groupId;
		this.groupUserId = groupUserId;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "group_follow_id", unique = true, nullable = false)
	public Integer getGroupFollowId() {
		return this.groupFollowId;
	}

	public void setGroupFollowId(Integer groupFollowId) {
		this.groupFollowId = groupFollowId;
	}

	@Column(name = "group_id")
	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@Column(name = "group_userId")
	public Integer getGroupUserId() {
		return this.groupUserId;
	}

	public void setGroupUserId(Integer groupUserId) {
		this.groupUserId = groupUserId;
	}

}