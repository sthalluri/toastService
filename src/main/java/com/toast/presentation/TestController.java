package com.toast.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toast.util.JSONException;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String test(@PathVariable("page") String page) throws JSONException {
		return "test/"+page;
	}
}
