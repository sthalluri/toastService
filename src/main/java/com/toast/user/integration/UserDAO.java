package com.toast.user.integration;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toast.user.service.User;

@Repository
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void save(User user) {
		if(user.getId()!=null){
			sessionFactory.getCurrentSession().update(user);
		}else{
			sessionFactory.getCurrentSession().save(user);
		}
	}

	public User get(Integer id) {
		return (User) sessionFactory.getCurrentSession().createQuery("from User where id = ?")
				.setInteger(0, id.intValue()).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> list() {
		System.out.println("Came to the users");
		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	public void delete(Integer id) {
		User user = (User) sessionFactory.getCurrentSession().load(User.class, id);
		if (null != user) {
			sessionFactory.getCurrentSession().delete(user);
		}
	}

	public User getByUserId(String userId) {
		return (User) sessionFactory.getCurrentSession().createQuery("from User where userId = ?")
		.setString(0, userId).uniqueResult();
	}

	public User getByAccessKey(String accessKey) {
		return (User) sessionFactory.getCurrentSession().createQuery("from User where accessKey = ?")
		.setString(0, accessKey).uniqueResult();
	}
}
