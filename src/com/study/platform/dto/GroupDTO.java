package com.study.platform.dto;

import java.io.File;

public class GroupDTO {

	String groupName;
	String groupDesc;
	File groupLogo;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupDesc() {
		return groupDesc;
	}
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	public File getGroupLogo() {
		return groupLogo;
	}
	public void setGroupLogo(File groupLogo) {
		this.groupLogo = groupLogo;
	};
	
	
}
