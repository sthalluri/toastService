package com.toast.meeting.integration;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toast.meeting.service.Meeting;
import com.toast.meeting.service.MeetingRoleContent;

@Repository
public class MeetingDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void save(Meeting meeting) {
		if (meeting.getId() != null) {
			sessionFactory.getCurrentSession().update(meeting);
		} else {
			sessionFactory.getCurrentSession().save(meeting);			
		}
	}

	public Meeting get(Integer id) {
		return (Meeting) sessionFactory.getCurrentSession()
				.createQuery("from Meeting where id = ?").setInteger(0, id.intValue())
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Meeting> list() {
		System.out.println("Came to the Meetings");
		return sessionFactory.getCurrentSession().createQuery("from Meeting order by meetingDate desc").list();
	}	

	public void delete(Integer id) {
		Meeting meeting = (Meeting) sessionFactory.getCurrentSession().load(Meeting.class, id);
		if (null != meeting) {
			sessionFactory.getCurrentSession().delete(meeting);
		}
	}

	public Meeting getByMeetingId(String meetingId) {
		return (Meeting) sessionFactory.getCurrentSession()
				.createQuery("from Meeting where MeetingId = ?").setString(0, meetingId)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Meeting> getByClubId(Integer clubId) {
		return sessionFactory.getCurrentSession().createQuery("from Meeting where clubId = ? order by meetingDate desc")
				.setInteger(0, clubId.intValue()).list();
	}
	
	
}
