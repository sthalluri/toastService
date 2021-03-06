package com.toast.user.presentation;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.toast.club.service.ClubService;
import com.toast.presentation.BaseController;
import com.toast.user.service.AuthToken;
import com.toast.user.service.User;
import com.toast.user.service.UserService;
import com.toast.util.JSONException;
import com.toast.util.JSONParser;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private UserService userService;
	
	@Autowired
	private ClubService clubService;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(Map<String, Object> map) throws JSONException {
		map.put("user", new User());
		map.put("json", JSONParser.listToJSON(userService.list()));
		return "json";
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
	public String get(@PathVariable("id") Integer userId, HttpSession session, ModelMap model)
			throws JSONException {
		User user = userService.get(userId);
		model.put("json", JSONParser.toJSON(user));
		return "json";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@RequestParam("json") String json, ModelMap model) throws JSONException {
		User user = (User) JSONParser.parseJSON(json, User.class);
		userService.register(response, user);
		model.put("json", response.toJson());
		return "json";
	}

	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	public String checkLogin(@RequestParam("json") String json, ModelMap model)
			throws JSONException {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		System.out.println("Session id :"+attrs.getSessionId());
		
		AuthToken authToken = (AuthToken) JSONParser.parseJSON(json, AuthToken.class);
		User user = userService.checkLogin(authToken);
		if (user !=null) {
			response.setSuccess(Boolean.TRUE);
			response.setReturnVal(user);
			response.addMessage("login.success");			
			request.getSession().setAttribute("LOGGED_IN_USER", user);
		} else {
			response.setSuccess(Boolean.FALSE);
			response.addError("login.failure");
		}
		model.put("json", response.toJson());
		return "json";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("id") Integer userId, ModelMap model)
	{
		User user = userService.get(userId);
		user.setIsEnabled('N');
		userService.save(user);
		response.setSuccess(Boolean.TRUE);
		model.put("json", response.toJson());
		
		return "json";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createUser(@RequestParam("json") String json, ModelMap model) throws JSONException
	{
		User user = (User)JSONParser.parseJSON(json, User.class);
		Integer id = user.getId();
		userService.save(user);
		if( id == null )
		{
			clubService.addMember(user.getDefaultClubId(), user.getId());
		}
		response.setSuccess(Boolean.TRUE);
		model.put("json", response.toJson());
		return "json";
	}
	
	
}
