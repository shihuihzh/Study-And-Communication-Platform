package com.study.platform.dto;

/**
 * 前台用户表单信息
 * @author fantasy
 *
 */
public class ApiUserFormDTO {

	long id;
	int pos;
	private String type;
	private String prefix;
	private String suffix;
	
	
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	
	
	
	
}
