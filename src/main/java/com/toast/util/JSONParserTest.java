/*
 * Current Status
 * --------------
 * $Author: ahuddleston $
 * $Source: /cvs/framework/src/mil/navy/msc/json/JSONParserTest.java,v $
 * $Date: 2010/07/30 17:59:37 $
 * $Revision: 1.7 $
 *
 */

package com.toast.util;

import java.util.ArrayList;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.toast.user.service.User;

/**
 * <p>
 * Test class for ShipDAO
 * </p>
 * 
 * @author smyneni
 * @version $Revision: 1.7 $ $Date: 2010/07/30 17:59:37 $
 */
public class JSONParserTest extends TestCase {

	/**
	 * Constructor for ShipDAOTest.
	 * 
	 * @param arg0
	 *            required param.
	 */
	public JSONParserTest(String arg0) {
		super(arg0);
	}

	@Test
	public void testToJSON() throws JSONException {

		User user = new User();
		user.setFirstName("test");
		user.setLastName("test");

		//Address addr = new Address();
		//addr.setAddress("testaddr");
		//user.setAddress(addr);

		String str = JSONParser.toJSON(user);

		System.out.println(str);

		user = (User) JSONParser.parseJSON(str, User.class);
		System.out.println(user.getFirstName());
	}

	@Test
	public void testListToJSON() throws JSONException {
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setFirstName("test");
		user.setLastName("test");
		users.add(user);

		//Address addr = new Address();
		//addr.setAddress("testaddr");
		//user.setAddress(addr);

		user = new User();
		user.setFirstName("test");
		user.setLastName("test");
		users.add(user);

		//addr = new Address();
		//addr.setAddress("testaddr");
		//user.setAddress(addr);

		String str = JSONParser.listToJSON(users);

		System.out.println(str);
	}
}
