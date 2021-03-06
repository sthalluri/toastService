package com.toast.presentation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.toast.service.Response;
import com.toast.user.service.User;

public class BaseController {

	@Autowired
	protected Response response;

	@Autowired
	protected HttpServletRequest request;

	protected User getUser() {
		User user = (User) request.getSession().getAttribute("LOGGED_IN_USER");
		return user;
	}

	protected String getUserId() {
		User user = (User) request.getSession().getAttribute("LOGGED_IN_USER");
		if(user==null){
			return null;
		}
		System.out.println("Accesskey:"+request.getParameter("accessKey"));
		if(request.getSession()!=null){
			System.out.println("SessionID"+request.getSession().getId());
		}else{
			System.out.println("SessionID is null");
		}
		return user.getUserId();
	}
}
