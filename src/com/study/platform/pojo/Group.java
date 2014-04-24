package com.study.platform.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Group entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "group", catalog = "study_communication")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Group implements java.io.Serializable {

	// Fields

	private Integer groupId;
	private String groupName;
	private String groupLogo;
	private String groupSmallLogo;
	private Long groupInterest;
	private String groupDesc;
	private Integer groupCreatorId;

	// Constructors

	/** default constructor */
	public Group() {
	}

	/** minimal constructor */
	public Group(String groupName) {
		this.groupName = groupName;
	}

	/** full constructor */
	public Group(String groupName, String groupLogo, Long groupInterest,
			String groupDesc, Integer groupCreatorId) {
		this.groupName = groupName;
		this.groupLogo = groupLogo;
		this.groupInterest = groupInterest;
		this.groupDesc = groupDesc;
		this.groupCreatorId = groupCreatorId;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "group_id", unique = true, nullable = false)
	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@Column(name = "group_name", nullable = false)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "group_logo")
	public String getGroupLogo() {
		return this.groupLogo;
	}

	public void setGroupLogo(String groupLogo) {
		this.groupLogo = groupLogo;
	}

	@Column(name = "group_interest")
	public Long getGroupInterest() {
		return this.groupInterest;
	}

	public void setGroupInterest(Long groupInterest) {
		this.groupInterest = groupInterest;
	}

	@Column(name = "group_desc", length = 65535)
	public String getGroupDesc() {
		return this.groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	@Column(name = "group_creator_id")
	public Integer getGroupCreatorId() {
		return this.groupCreatorId;
	}
	
	
	@Column(name = "group_samll_logo")
	public String getGroupSmallLogo() {
		return groupSmallLogo;
	}

	public void setGroupSmallLogo(String groupSmallLogo) {
		this.groupSmallLogo = groupSmallLogo;
	}

	public void setGroupCreatorId(Integer groupCreatorId) {
		this.groupCreatorId = groupCreatorId;
	}

}