package com.toast.user.service;

import org.junit.Test;

import com.toast.meeting.service.Meeting;
import com.toast.meeting.service.MeetingParser;

public class MeetingParserTest {

	@Test
	public void testFromJson(){
		String json = "{\"id\":1,\"wordOfTheDay\":\"Mark My Words\",\"updated\":\"2011-05-25 15:32:26.0\",\"created\":\"2011-05-25 15:32:26.0\",\"roles\":{\"test\":{\"id\":1,\"amCount\":\"{am:10}\",\"created\":\"2011-05-25 15:32:26.0\",\"roleId\":\"test\"}},\"themeOfTheDay\":\"Nice Theme\",\"inProgress\":false,\"meetingDate\":\"2011-05-25 15:32:25.0\"}";
		Meeting meeting = MeetingParser.fromJson(json);
		System.out.println(MeetingParser.toJson(meeting));
	}
}
