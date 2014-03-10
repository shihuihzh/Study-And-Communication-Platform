package com.study.platform.dto;

/**
 * 前台用户表单信息
 * @author fantasy
 *
 */
public class UserFormDTO {

	long id;
	String email;
	String password;
	String nickname;
	String sexual;
	String birthday;
	String stayNow;
	String personalSite;
	String selfIntroduction;
	String occupation;
	
	
	
	
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSexual() {
		return sexual;
	}
	public void setSexual(String sexual) {
		this.sexual = sexual;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getStayNow() {
		return stayNow;
	}
	public void setStayNow(String stayNow) {
		this.stayNow = stayNow;
	}
	public String getPersonalSite() {
		return personalSite;
	}
	public void setPersonalSite(String personalSite) {
		this.personalSite = personalSite;
	}
	public String getSelfIntroduction() {
		return selfIntroduction;
	}
	public void setSelfIntroduction(String selfIntroduction) {
		this.selfIntroduction = selfIntroduction;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
