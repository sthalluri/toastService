package com.toast.user.service;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.MessageSource;

import com.toast.service.BaseServiceTest;
import com.toast.util.JSONException;
import com.toast.util.JSONParser;

public class UserServiceTest extends BaseServiceTest {

	private UserService userService;	


	@Test
	public void testGet() throws JSONException {
		User user = userService.get(1);
		System.out.println(JSONParser.toJSON(user));
	}
	
	@Test
	public void testSave() {
		User user = new User();
		user.setUserId("guest");
		user.setPassword("guest");
		user.setFirstName("Gest One");
		user.setLastName("Last Name");
		user.setEmail("sthalluri@yhoo.com");
		user.setPhone("07345345435");
		user.setDefaultClubId(1);
		userService.save(user);
		
		MessageSource resources = (MessageSource) appContext.getBean("messageSource");
		
		System.out.println(resources.getMessage("customer.name",null, Locale.ENGLISH));
			
		System.out.println(userService.list());
		
	}
	
	@Test
	public void testRegister() {
		User user = new User();
		user.setUserId("guest");
		user.setPassword("guest");
		user.setFirstName("My Name");
		user.setLastName("Last Name");
		user.setEmail("sthalluri@yhoo.com");
		user.setPhone("07345345435");
		
		userService.register(response,user);
		//Response response = userService.register(user);
		System.out.println(response.toJson());		
	}

	@Before
	public void setup(){
		super.setup();
		userService = (UserService) factory.getBean("userService");
	}
}
