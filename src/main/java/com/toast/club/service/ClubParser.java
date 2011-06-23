package com.toast.club.service;

//import org.codehaus.jettison.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.toast.user.service.User;
import com.toast.util.StringHelper;

public class ClubParser {
	
	public static String toJson(Club club) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("id", club.getId().intValue());
			obj.put("clubId", club.getClubId());
			obj.put("clubName", club.getClubName());
			String clubSetting = club.getClubSettings();
			JSONObject clubSettingJson = new JSONObject();
			if(StringHelper.isValid(clubSetting)){
		        JSONTokener gramTorkenizer = new JSONTokener(clubSetting);
		        clubSettingJson  = (JSONObject) gramTorkenizer.nextValue();
			}
			obj.put("clubSettings", clubSettingJson);

			//JSONArray jsonArray = new JSONArray();
			JSONObject members = new JSONObject();
			for (User member : club.getClubMembers()) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", member.getId());
				jsonObj.put("userId", member.getUserId());
				jsonObj.put("firstName", member.getFirstName());
				jsonObj.put("lastName", member.getLastName());
				jsonObj.put("email", member.getEmail());
				jsonObj.put("phone", member.getPhone());
				jsonObj.put("aboutMe", member.getAboutMe());
				members.put(member.getId().toString(), jsonObj);
			}
			obj.put("members", members);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj.toString();
	}

	public static Club fromJson(String json) {
        JSONTokener tokenizer = new JSONTokener(json);
        Club club = new Club();
		JSONObject obj;
		try {
            obj = (JSONObject) tokenizer.nextValue();
            if (!obj.isNull("id")) {
            	club.setId(obj.getInt("id"));
            }
            if (!obj.isNull("clubSettings")) {
                JSONObject clubSettings = obj.getJSONObject("clubSettings");
                club.setClubSettings(clubSettings.toString());
            }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return club;
	}

}

