package com.toast.meeting.service;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meetingRoleContent")
public class MeetingRoleContent {
	@Id
	@Column
	@GeneratedValue
	private Integer id;

	@Column
	private Integer meetingRoleId;

	@Column
	private String content;

	@Column
	private Integer userId;

	@Column
	private Date updated;

	@Column
	private Date created;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMeetingRoleId() {
		return meetingRoleId;
	}

	public void setMeetingRoleId(Integer meetingRoleId) {
		this.meetingRoleId = meetingRoleId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	
}
