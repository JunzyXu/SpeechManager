package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Speech {
	/*
	 * 演讲主题
	 */
	private String title;
	/*
	 * 组员名单
	 */
	private List<String> groupMumber = new ArrayList<String>();
	/*
	 * 演讲内容
	 */
	private String content;

	/*
	 * 申请日期
	 */
	private String applyDate;

	public Speech(Speech speech) {
		this.groupMumber = speech.getGroupMumber();
		this.content = speech.getContent();
		this.title = speech.getTitle();
	}

	public Speech(String title, List<String> groupMumber, String content) {
		this.groupMumber = groupMumber;
		this.content = content;
		this.title = title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setGroupMumber(List<String> groupMumber) {
		this.groupMumber = groupMumber;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return this.title;
	}

	public List<String> getGroupMumber() {
		return this.groupMumber;
	}

	public String getGroupLeader() {
		if (groupMumber != null && groupMumber.size() != 0)
			return groupMumber.get(0);
		return "has not leader";
	}

	public String getContent() {
		return this.content;
	}
}
