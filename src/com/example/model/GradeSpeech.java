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
public class GradeSpeech extends Speech implements Serializable {
	/*
	 * 得分   
	 */
	private int grade;
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

	private long gradeID;

	public GradeSpeech(GradeSpeech speech) {
		super(speech);
		this.grade = speech.getGrade();
		this.gradeID = speech.getGradeID();
	}

	public GradeSpeech(long gradeID, String title, List<String> groupMumber,
			String content, int grade, String teachComment, String gradeDate,
			String pptAddress) {
		super(title, groupMumber, content);
		this.grade = grade;
		this.teachComment = teachComment;
		this.pptAddress = pptAddress;
		this.gradeDate = gradeDate;
		this.gradeID = gradeID;

	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public void setGradeID(long gradeID) {
		this.gradeID = gradeID;
	}

	public void setTeachComment(String teachComment) {
		this.teachComment = teachComment;
	}

	public void setPPTAddress(String pptAddress) {
		this.pptAddress = pptAddress;
	}

	public void setGradeDate(String gradeDate) {
		this.gradeDate = gradeDate;
	}

	public long getGradeID() {
		return this.gradeID;
	}

	public String getPPTAddress() {
		return this.pptAddress;
	}

	public String getTeachComment() {
		return this.teachComment;
	}

	public String getGradeDate() {
		return this.gradeDate;
	}

	public int getGrade() {
		return this.grade;
	}

}
