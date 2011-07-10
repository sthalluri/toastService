package com.toast.club.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.toast.user.service.User;

/**
 * @author sthalluri
 *
 */
@Entity
@Table(name = "CLUB")
public class Club {
	@Id
	@Column
	@GeneratedValue
	private Integer id;

	@Column
	private String clubId;

	@Column
	private String clubName;

	@Column
	private String clubSettings;

	@Column
	private String passCode;

	@Column
	private Date updated;

	@Column
	private Date created;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "clubMember", joinColumns = { @JoinColumn(name = "clubId") }, inverseJoinColumns = { @JoinColumn(name = "memberId") })
	private List<User> clubMembers;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClubId() {
		return clubId;
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}
	

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getPassCode() {
		return passCode;
	}

	public void setPassCode(String passCode) {
		this.passCode = passCode;
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

	public List<User> getClubMembers() {
		List<User> enabledMembers = new ArrayList<User>();
		for(int i = 0; i < clubMembers.size(); i++)
		{
			User member = clubMembers.get(i);
			if(member.getIsEnabled() == 'Y')
			{
				enabledMembers.add(member);
			}
		}
		return enabledMembers;
	}

	public void setClubMembers(List<User> clubMembers) {
		this.clubMembers = clubMembers;
	}

	public String getClubSettings() {
		return clubSettings;
	}

	public void setClubSettings(String clubSettings) {
		this.clubSettings = clubSettings;
	}
}
