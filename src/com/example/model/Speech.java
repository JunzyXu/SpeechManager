package com.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * jormin
 * 2014-06-04 
 */

/**
 * 演讲类 封装演讲信息
 */
@SuppressWarnings("serial")
public class Speech implements Serializable {
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
	 * 得分
	 */
	private int grade;
	/*
	 * 申请日期
	 */
	private String applyDate;
	/*
	 * 评分日期
	 */
	private String gradeDate;
	/*
	 * 老师点评
	 */
	private String teachComment;
	/*
	 * ppt下载地址
	 */
	private String pptAddress;

	private long groupID;

	public Speech(Speech speech) {
		this.groupMumber = speech.getGroupMumber();
		this.content = speech.getContent();
		this.grade = speech.getGrade();
		this.title = speech.getTitle();
		this.groupID = speech.getGroupID();
	}

	public Speech(String title, List<String> groupMumber, String content,
			int grade) {
		this.groupMumber = groupMumber;
		this.content = content;
		this.grade = grade;
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

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public void setGroupID(long groupID) {
		this.groupID = groupID;
	}

	public long getGroupID() {
		return this.groupID;
	}

	public String getTitle() {
		return this.title;
	}

	public List<String> getGroupMumber() {
		return this.groupMumber;
	}

	public int getGrade() {
		return this.grade;
	}

	public String getContent() {
		return this.content;
	}

}
