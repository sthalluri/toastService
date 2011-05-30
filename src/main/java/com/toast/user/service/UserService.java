package com.toast.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toast.club.integration.ClubDAO;
import com.toast.club.service.Club;
import com.toast.service.BaseService;
import com.toast.service.Response;
import com.toast.user.integration.UserDAO;

@Service
public class UserService extends BaseService{

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ClubDAO clubDAO;

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
			
			//Create a personal club for this user and save it
			Club club = new Club();
			club.setClubId("PERSONAL");
			club.setClubName("MYCLUB");
			
			List<User> clubMembers = new ArrayList<User>();
			clubMembers.add(user);
			club.setClubMembers(clubMembers);
			
			clubDAO.save(club);
			
			//Set the new club as the default club for this user 
			user.setDefaultClubId(club.getId());
			save(user);
			
			response.setSuccess(Boolean.TRUE);
		}
	}

}
