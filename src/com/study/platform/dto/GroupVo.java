package com.study.platform.dto;

import java.util.List;

import org.jsoup.Jsoup;

import com.study.platform.pojo.Group;
import com.study.platform.pojo.Question;

public class GroupVo {

	private Group group;
	private List<Question> question;
	private String desc;
	private boolean followed;

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
		String d = Jsoup.parse(group.getGroupDesc()).body().text();
		desc = d.substring(0, 150);
		if(d.length() > 150) {
			desc += "...";
		}
		
	}

	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isFollowed() {
		return followed;
	}

	public void setFollowed(boolean followed) {
		this.followed = followed;
	}

	
	
	

	
	
	
	
	
	
}
