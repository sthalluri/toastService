package com.toast.presentation;

import org.springframework.beans.factory.annotation.Autowired;

import com.toast.service.Response;

public class BaseController {

	@Autowired
	protected Response response;

}
