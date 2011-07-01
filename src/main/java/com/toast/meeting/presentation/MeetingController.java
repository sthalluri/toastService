package com.toast.meeting.presentation;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.toast.meeting.service.Meeting;
import com.toast.meeting.service.MeetingParser;
import com.toast.meeting.service.MeetingRoleContent;
import com.toast.meeting.service.MeetingService;
import com.toast.presentation.BaseController;
import com.toast.util.JSONException;
import com.toast.util.JSONParser;

@Controller
@RequestMapping("/meeting")
public class MeetingController extends BaseController{

	@Autowired
	private MeetingService meetingService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Map<String, Object> map) throws JSONException {
		response.setReturnVal(meetingService.list());
		map.put("json", response.toJson());
		return "json";
	}

	@RequestMapping(value = "/getByClubId/{id}", method = RequestMethod.GET)
	public String getByClubId(@PathVariable("id") Integer clubId, Map<String, Object> map) throws JSONException {
		System.out.println("Logged In As :"+getUserId());
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		System.out.println("Session id :"+attrs.getSessionId());

		response.setReturnVal(meetingService.getByClubId(clubId));
		map.put("json", response.toJson());
		return "json";
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public String get(@PathVariable("id") Integer meetingId, HttpSession session, ModelMap model)
			throws JSONException {
		System.out.println("Logged In As :"+getUserId());
		Meeting meeting = meetingService.get(meetingId);
		model.put("json", MeetingParser.toJson(meeting));
		return "json";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestParam("json") String json, ModelMap model) throws JSONException {
		System.out.println("Logged In As :"+getUser());
		Meeting meeting = (Meeting) MeetingParser.fromJson(json);
		meetingService.save(meeting);
		response.setReturnVal(meeting);
		response.addMessage("msg.savesuccessful");
		model.put("json", response.toJson());
		return "json";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer meetingId, HttpSession session, ModelMap model) {
		Meeting meeting = meetingService.get(meetingId);
		meetingService.delete(meetingId);
		response.setReturnVal(meetingService.getByClubId(meeting.getClubId()));
		response.addMessage("msg.savesuccessful");
		model.put("json", response.toJson());
		return "json";
	}
	
	@RequestMapping(value = "/getContent/{id}", method = RequestMethod.GET)
	public String getContent(@PathVariable("id") Integer meetingRoleId, HttpSession session, ModelMap model)
			throws JSONException {
		response.setReturnVal(meetingService.getContent(meetingRoleId));
		model.put("json", response.toJson());
		return "json";
	}

	@RequestMapping(value = "/saveContent", method = RequestMethod.POST)
	public String saveContent(@RequestParam("json") String json, ModelMap model) throws JSONException {
		MeetingRoleContent content = (MeetingRoleContent) JSONParser.parseJSON(json,MeetingRoleContent.class);
		meetingService.saveContent(content);
		response.setReturnVal(content);
		response.addMessage("msg.savesuccessful");
		model.put("json", response.toJson());
		return "json";
	}

}
