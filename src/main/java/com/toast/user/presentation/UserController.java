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

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Map<String, Object> map) throws JSONException {
		map.put("user", new User());
		map.put("json", JSONParser.listToJSON(userService.list()));
		return "json";
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
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
		AuthToken authToken = (AuthToken) JSONParser.parseJSON(json, AuthToken.class);
		User user = userService.checkLogin(authToken);
		if (user !=null) {
			response.setSuccess(Boolean.TRUE);
			response.setReturnVal(user);
			response.addMessage("login.success");
		} else {
			response.setSuccess(Boolean.FALSE);
			response.addError("login.failure");
		}
		model.put("json", response.toJson());
		return "json";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer userId) {
		userService.delete(userId);
		return "redirect:/index";
	}

	
	
	
}
