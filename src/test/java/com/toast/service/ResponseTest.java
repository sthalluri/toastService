package com.toast.service;

import org.junit.Test;

import com.toast.user.service.User;
import com.toast.util.JSONException;

public class ResponseTest {

	@Test
	public void testBasic() throws JSONException{
		Response resp = new Response();
		System.out.println(resp.toJson());
	}

	@Test
	public void testSuccess() throws JSONException{
		Response resp = new Response();
		resp.setSuccess(Boolean.TRUE);
		System.out.println(resp.toJson());
	}

	@Test
	public void testFullSuccess() throws JSONException{
		Response resp = new Response();
		resp.setSuccess(Boolean.TRUE);
		resp.setReturnName("user");
		User user = new User();
		user.setFirstName("test");
		user.setLastName("test2");
		resp.setReturnVal(user);
		resp.setSuccessMessage("Found the user");
		System.out.println(resp.toJson());
	}

	@Test
	public void testError() throws JSONException{
		Response resp = new Response();
		resp.setSuccess(Boolean.FALSE);
		resp.setErrorMessage("Did not find the user");
		System.out.println(resp.toJson());
	}

}
