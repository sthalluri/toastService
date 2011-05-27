package com.toast.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toast.service.BaseService;
import com.toast.service.Response;
import com.toast.user.integration.UserDAO;

@Service
public class UserService extends BaseService{

	@Autowired
	private UserDAO userDAO;
	
	@Transactional
	public void save(User user) {
		userDAO.save(user);
	}

	@Transactional
	public List<User> list() {
		return userDAO.list();
	}

	@Transactional
	public void delete(Integer id) {
		userDAO.delete(id);
	}

	@Transactional
	public User get(Integer id) {
		return userDAO.get(id);
	}

	@Transactional
	public User getByUserId(String userId) {
		return userDAO.getByUserId(userId);
	}

	@Transactional
	public User checkLogin(AuthToken authToken){
		User user = userDAO.getByUserId(authToken.getUserId());
		if(authToken.getPassword() !=null && user !=null && authToken.getPassword().equals(user.getPassword())){
			return user;
		}else{
			return null;
		}
	}

	@Transactional
	public void register(Response response, User user) {
		User existingUser = getByUserId(user.getUserId());
		response.setReturnVal(user);
		if(existingUser != null){
			response.setSuccess(Boolean.FALSE);
			response.addError("error.user.exists");
		}else{
			save(user);
			response.setSuccess(Boolean.TRUE);
		}
	}

}
