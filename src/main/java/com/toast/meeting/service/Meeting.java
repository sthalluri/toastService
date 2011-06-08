package com.toast.meeting.service;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "meeting")
public class Meeting {
	@Id
	@Column
	@GeneratedValue
	private Integer id;

	@Column
	private Integer clubId;

	@Column
	private Boolean inProgress;

	@Column
	private String wordOfTheDay;

	@Column
	private String themeOfTheDay;

	@Column
	private Date meetingDate;

	@Column
	private String location;

	@Column
	private Date updated;

	@Column
	private Date created;

	@OneToMany(mappedBy = "meeting", targetEntity = MeetingRole.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<MeetingRole> meetingRoles;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getInProgress() {
		return inProgress;
	}

	public void setInProgress(Boolean inProgress) {
		this.inProgress = inProgress;
	}

	public String getWordOfTheDay() {
		return wordOfTheDay;
	}

	public void setWordOfTheDay(String wordOfTheDay) {
		this.wordOfTheDay = wordOfTheDay;
	}

	public String getThemeOfTheDay() {
		return themeOfTheDay;
	}

	public void setThemeOfTheDay(String string) {
		this.themeOfTheDay = string;
	}

	public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
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

	public List<MeetingRole> getMeetingRoles() {
		return meetingRoles;
	}

	public void setMeetingRoles(List<MeetingRole> meetingRoles) {
		this.meetingRoles = meetingRoles;
	}

	public Integer getClubId() {
		return clubId;
	}

	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Meeting [id=" + id + ", inProgress=" + inProgress + ", wordOfTheDay="
				+ wordOfTheDay + ", themeOfTheDay=" + themeOfTheDay + ", meetingDate="
				+ meetingDate + ", updated=" + updated + ", created=" + created + ", meetingRoles="
				+ meetingRoles + "]";
	}
	
}
