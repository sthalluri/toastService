package com.toast.club.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.toast.service.BaseServiceTest;
import com.toast.user.service.User;
import com.toast.user.service.UserService;
import com.toast.util.JSONException;

public class ClubServiceTest extends BaseServiceTest {

	@Autowired
	private ClubService clubService;

	@Autowired
	private UserService userService;

	@Test
	public void testGet() throws JSONException {
		Club club = clubService.get(1);
	}

	@Test
	public void testAdd() throws JSONException {
		clubService.addMember(1, 3);
	}
	
	@Test
	public void testSave() {
		Club club = new Club();
		club.setClubId("FFCLUB");
		club.setPassCode("SECRET");
		club.setClubName("Fairfax Club");
		
		User user = userService.get(1);
		
		List<User> users = new ArrayList<User>();
		users.add(user);
		club.setClubMembers(users);
		
		clubService.save(club);
	}

	@Before
	public void setup() {
		super.setup();
		clubService = (ClubService) factory.getBean("clubService");
		userService = (UserService) factory.getBean("userService");
	}

}
