package com.toast.meeting.integration;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toast.meeting.service.MeetingRoleContent;

@Repository
public class MeetingRoleContentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void save(MeetingRoleContent content){
		if (content.getId() != null) {
			sessionFactory.getCurrentSession().update(content);
		} else {
			sessionFactory.getCurrentSession().save(content);
		}
	}

	public MeetingRoleContent get(Integer id) {
		return (MeetingRoleContent) sessionFactory.getCurrentSession()
				.createQuery("from MeetingRoleContent where id = ?")
				.setInteger(0, id.intValue()).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<MeetingRoleContent> list(Integer meetingRoleId) {
		return sessionFactory.getCurrentSession()
				.createQuery("from MeetingRoleContent where meetingRoleId = ?")
				.setInteger(0, meetingRoleId.intValue()).list();
	}
}
