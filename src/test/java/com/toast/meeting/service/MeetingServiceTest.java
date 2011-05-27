package com.toast.meeting.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.toast.service.BaseServiceTest;
import com.toast.util.JSONException;

public class MeetingServiceTest extends BaseServiceTest {

	@Autowired
	private MeetingService meetingService;

	@Test
	public void testGet() throws JSONException {
		Meeting meeting = meetingService.get(1);
		
		//List<MeetingRole> roles = meeting.getMeetingRoles();		
		System.out.println(MeetingParser.toJson(meeting));
	}

	
	@Test
	public void testSave() {
		Meeting meeting = new Meeting();
		meeting.setInProgress(Boolean.FALSE);
		meeting.setMeetingDate(new Date());
		meeting.setThemeOfTheDay("Nice Theme");
		meeting.setWordOfTheDay("Mark My Words");
		
		MeetingRole role = new MeetingRole();
		role.setMeeting(meeting);
		role.setRoleId("test");
		role.setAmCount("{am:10}");
		
		List<MeetingRole> roles = new ArrayList<MeetingRole>();
		roles.add(role);
		
		meeting.setMeetingRoles(roles);
		
		meetingService.save(meeting);
		//System.out.println(JSONParser.listToJSONString(meetingService.list()));
	}
	
	@Test
	public void testUpdate(){
		String json = "{\"id\":1,\"clubId\":1,\"wordOfTheDay\":\"Dont My Words\",\"updated\":\"2011-05-25 15:32:26.0\",\"created\":\"2011-05-25 15:32:26.0\",\"roles\":{" +
				"\"test1\":{\"id\":1,\"amCount\":\"{ams:20}\",\"created\":\"2011-05-25 15:32:26.0\",\"roleId\":\"test\"}" +
				",\"test2\":{\"amCount\":\"{ams:20}\",\"created\":\"2011-05-25 15:32:26.0\",\"roleId\":\"test\"}" +
				"},\"themeOfTheDay\":\"Nice Theme\",\"inProgress\":false,\"meetingDate\":\"2011-05-25 15:32:25.0\"}";
		Meeting meeting = MeetingParser.fromJson(json);
		meetingService.save(meeting);		
	}
	
	@Test
	public void testList(){
		String json = "{\"id\":1,\"wordOfTheDay\":\"Dont My Words\",\"updated\":\"2011-05-25 15:32:26.0\",\"created\":\"2011-05-25 15:32:26.0\",\"roles\":{" +
				"\"test1\":{\"id\":1,\"amCount\":\"{ams:20}\",\"created\":\"2011-05-25 15:32:26.0\",\"roleId\":\"test\"}" +
				",\"test2\":{\"amCount\":\"{ams:20}\",\"created\":\"2011-05-25 15:32:26.0\",\"roleId\":\"test\"}" +
				"},\"themeOfTheDay\":\"Nice Theme\",\"inProgress\":false,\"meetingDate\":\"2011-05-25 15:32:25.0\"}";
		Meeting meeting = MeetingParser.fromJson(json);
		meetingService.save(meeting);		
	}
	
	@Before
	public void setup(){
		super.setup();
		meetingService = (MeetingService) factory.getBean("meetingService");
	}

}
