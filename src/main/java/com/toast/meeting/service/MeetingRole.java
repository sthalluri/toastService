package com.toast.meeting.service;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

@Entity
@Table(name = "meetingRole")
public class MeetingRole {
	@Id
	@Column
	@GeneratedValue
	private Integer id;

	@XStreamOmitField
	@ManyToOne
	@JoinColumn(name="meetingId")
	private Meeting meeting;

	@Column
	private String roleId;

	@Column
	private Integer timeSpent;

	@Column
	private String amCount;

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


	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Integer getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(Integer timeSpent) {
		this.timeSpent = timeSpent;
	}

	public String getAmCount() {
		return amCount;
	}

	public void setAmCount(String amCount) {
		this.amCount = amCount;
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

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "MeetingRole [id=" + id + ", meeting=" + meeting + ", roleId=" + roleId
				+ ", timeSpent=" + timeSpent + ", amCount=" + amCount + ", userId=" + userId
				+ ", updated=" + updated + ", created=" + created + "]";
	}
}
