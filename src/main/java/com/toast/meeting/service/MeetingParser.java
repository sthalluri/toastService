package com.toast.meeting.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.toast.util.StringHelper;

public class MeetingParser {

	
	//{"id":19,"wordOfTheDay":"Mark My Words","updated":"2011-05-21 23:14:28.0","created":"2011-05-21 23:14:28.0",
	//"meetingRoles":[{"id":12,"amCount":"{am:10}","created":"2011-05-21 23:14:28.0","roleId":"test"}],"themeOfTheDay":"Nice Theme","inProgress":false,"meetingDate":"2011-05-21 23:14:28.0"}

	public static String toJson(Meeting meeting) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("id", meeting.getId().intValue());
			if(meeting.getClubId()!=null){
				obj.put("clubId", meeting.getClubId().intValue());
			}
			obj.put("created", meeting.getCreated());
			obj.put("inProgress", meeting.getInProgress());
			obj.put("meetingDate", meeting.getMeetingDate());
			obj.put("themeOfTheDay", meeting.getThemeOfTheDay());
			obj.put("wordOfTheDay", meeting.getWordOfTheDay());
			obj.put("location", meeting.getLocation());
			//JSONArray jsonArray = new JSONArray();
			JSONObject roles = new JSONObject();
			for (MeetingRole role : meeting.getMeetingRoles()) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", role.getId());
				String amCount = role.getAmCount();
				JSONObject amJsonObj = new JSONObject();
				if(StringHelper.isValid(amCount)){
			        JSONTokener amTokenizer = new JSONTokener(amCount);
			        amJsonObj  = (JSONObject) amTokenizer.nextValue();
				}
				jsonObj.put("amCount", amJsonObj);
				jsonObj.put("created", role.getCreated());
				jsonObj.put("roleId", role.getRoleId());
				jsonObj.put("userId", role.getUserId());
				if (role.getTimeSpent() != null) {
					jsonObj.put("timeSpent", role.getTimeSpent().intValue());
				}
				obj.put("updated", role.getUpdated());
				roles.put(role.getRoleId(), jsonObj);
			}
			obj.put("roles", roles);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj.toString();
	}

	public static Meeting fromJson(String json) {
        JSONTokener tokenizer = new JSONTokener(json);
        Meeting meeting = new Meeting();
		JSONObject obj;
		try {
            obj = (JSONObject) tokenizer.nextValue();
            if (!obj.isNull("id")) {
                meeting.setId(obj.getInt("id"));
            }
            if (!obj.isNull("created")) {
                meeting.setCreated(formatToDate(obj.getString("created")));
            }
            if (!obj.isNull("inProgress")) {
                meeting.setInProgress(obj.getBoolean("inProgress"));
            }
            if (!obj.isNull("meetingDate")) {
                meeting.setMeetingDate(formatToDate(obj.getString("meetingDate")));
            }
            if (!obj.isNull("themeOfTheDay")) {
            	meeting.setThemeOfTheDay(obj.getString("themeOfTheDay"));
            }
            if (!obj.isNull("wordOfTheDay")) {
            	meeting.setWordOfTheDay(obj.getString("wordOfTheDay"));
            }
            if (!obj.isNull("clubId")) {
            	meeting.setClubId(obj.getInt("clubId"));
            }
            if (!obj.isNull("location")) {
            	meeting.setLocation(obj.getString("location"));
            }
            List<MeetingRole> roles = new ArrayList<MeetingRole>();
            JSONObject jRoles = obj.getJSONObject("roles");
            Iterator<String> keys = jRoles.keys();
            while(keys.hasNext()){
            	String key = keys.next();
            	JSONObject jRole = jRoles.getJSONObject(key);
            	MeetingRole role = new MeetingRole();
            	role.setRoleId(key);
                if (!jRole.isNull("id")) {
                	role.setId(jRole.getInt("id"));
                }
                if (!jRole.isNull("amCount")) {
                    JSONObject amCount = jRole.getJSONObject("amCount");
                	role.setAmCount(amCount.toString());
                }
            	if (!jRole.isNull("timeSpent")) {
            		role.setTimeSpent(jRole.getInt("timeSpent"));
            	}
            	if (!jRole.isNull("userId")&& StringHelper.isValid(jRole.getString("userId"))) {
            		int userId = jRole.getInt("userId");
            		if(userId > 0){
                		role.setUserId(userId);
            		}
            	}
            	role.setMeeting(meeting);
            	roles.add(role);
            }
            meeting.setMeetingRoles(roles);            
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return meeting;
	}

	private static Date formatToDate(String string) {
		return null;
	}

}
