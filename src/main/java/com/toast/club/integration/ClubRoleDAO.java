package com.toast.club.integration;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toast.club.service.ClubRole;

@Repository
public class ClubRoleDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<ClubRole> list() {
		return sessionFactory.getCurrentSession().createQuery("from ClubRole").list();
	}
}
