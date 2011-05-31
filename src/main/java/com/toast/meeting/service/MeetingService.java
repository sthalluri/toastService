package com.toast.meeting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toast.meeting.integration.MeetingDAO;
import com.toast.meeting.integration.MeetingRoleContentDAO;

@Service
public class MeetingService {

	@Autowired
	private MeetingDAO meetingDAO;
	
	@Autowired
	MeetingRoleContentDAO meetingRoleContentDAO;

	@Transactional
	public void save(Meeting meeting) {
		meetingDAO.save(meeting);
	}

	@Transactional
	public List<Meeting> list() {
		return meetingDAO.list();
	}

	@Transactional
	public void delete(Integer id) {
		meetingDAO.delete(id);
	}

	@Transactional
	public Meeting get(Integer id) {
		return meetingDAO.get(id);
	}

	@Transactional
	public List<Meeting>  getByClubId(Integer clubId) {
		return meetingDAO.getByClubId(clubId);
	}

	@Transactional
	public List<MeetingRoleContent>  getContent(Integer meetingRoleId) {
		return meetingRoleContentDAO.list(meetingRoleId);
	}

	@Transactional
	public void  saveContent(MeetingRoleContent content) {
		meetingRoleContentDAO.save(content);
	}

}
