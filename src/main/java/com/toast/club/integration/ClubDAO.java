package com.toast.club.integration;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toast.club.service.Club;

@Repository
public class ClubDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void save(Club club) {
		if (club.getId() != null) {
			sessionFactory.getCurrentSession().update(club);
		} else {
			sessionFactory.getCurrentSession().save(club);			
		}
	}

	public Club get(Integer id) {
		return (Club) sessionFactory.getCurrentSession()
				.createQuery("from Club where id = ?").setInteger(0, id.intValue())
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Club> list() {
		System.out.println("Came to the Clubs");
		return sessionFactory.getCurrentSession().createQuery("from Club").list();
	}

	public void delete(Integer id) {
		Club club = (Club) sessionFactory.getCurrentSession().load(Club.class, id);
		if (null != club) {
			sessionFactory.getCurrentSession().delete(club);
		}
	}

	public Club getByClubId(String clubId) {
		return (Club) sessionFactory.getCurrentSession()
				.createQuery("from Club where ClubId = ?").setString(0, clubId)
				.uniqueResult();
	}
}
