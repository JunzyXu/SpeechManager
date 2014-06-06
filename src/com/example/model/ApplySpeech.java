package com.example.model;

import java.util.List;

public class ApplySpeech extends Speech {
	/*
	 * 申请演讲时长
	 */
	private String time;
	/*
	 * 申请演讲所属方向
	 */
	private String direction;
	/*
	 * 申请ID
	 */
	private long applyID;

	public ApplySpeech(ApplySpeech applySpeech) {
		super(applySpeech);
		this.time = applySpeech.getTime();
		this.direction = applySpeech.getDirection();
	}

	public ApplySpeech(long applyID, String title, List<String> groupMumber,
			String content, String time, String direction) {
		super(title, groupMumber, content);
		this.applyID = applyID;
		this.time = time;
		this.direction = direction;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setApplyID(long applyID) {
		this.applyID = applyID;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public long getApplyID() {
		return applyID;
	}

	public String getTime() {
		return time;
	}

	public String getDirection() {
		return direction;
	}
}
