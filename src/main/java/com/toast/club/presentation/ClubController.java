package com.toast.club.presentation;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.toast.club.service.Club;
import com.toast.club.service.ClubParser;
import com.toast.club.service.ClubRole;
import com.toast.club.service.ClubService;
import com.toast.service.Response;
import com.toast.user.service.User;
import com.toast.util.JSONException;
import com.toast.util.JSONParser;

@Controller
@RequestMapping("/club")
public class ClubController {

	@Autowired
	private ClubService clubService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(Map<String, Object> map) throws JSONException {
		map.put("json", JSONParser.listToJSON(clubService.list()));
		return "json";
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
	public String get(@PathVariable("id") Integer clubId, HttpSession session, ModelMap model)
			throws JSONException {
		Response response = new Response();
		Club club = clubService.get(clubId);
		response.setReturnJson(ClubParser.toJson(club));
		model.put("json", response.toJson());
		return "json";
	}

	@RequestMapping(value = "/getClubMembers/{id}", method = RequestMethod.POST)
	public String getClubMembers(@PathVariable("id") Integer clubId, HttpSession session, ModelMap model)
			throws JSONException {
		Response response = new Response();
		Club club = clubService.get(clubId);
		List<User> clubMembers = club.getClubMembers();
		response.setReturnVal(clubMembers);
		model.put("json", response.toJson());
		return "json";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestParam("json") String json, ModelMap model) throws JSONException {
		Club club = (Club) JSONParser.parseJSON(json,Club.class);
		clubService.save(club);
		model.put("json", JSONParser.toJSON(club));
		return "json";
	}

	@RequestMapping(value = "/saveSettings", method = RequestMethod.POST)
	public String saveSettings(@RequestParam("json") String json, ModelMap model) throws JSONException {
		Response response = new Response();
		Club club = ClubParser.fromJson(json);
		clubService.saveSettings(club);
		model.put("json", response.toJson());
		return "json";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer clubId) {
		clubService.delete(clubId);
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/clubRoleList", method = RequestMethod.POST)
	public String clubRoleList(ModelMap model) throws JSONException {
		Response response = new Response();
		List<ClubRole> roles = clubService.getClubRoles();
		if(roles.size()==0){
			System.out.println(roles.size());
		}
		response.setReturnVal(roles);
		model.put("json", response.toJson());
		return "json";
	}
}
