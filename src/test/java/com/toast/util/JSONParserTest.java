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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.toast.meeting.service.MeetingParser;
import com.toast.user.service.User;

/**
 * <p>
 * Test class for ShipDAO
 * </p>
 * 
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
	
	@Test
	public void testDateParsing() throws ParseException{
		Date date = MeetingParser.formatToDate("1980-01-01T11:00:00.000Z");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.s'Z'");
		df.setTimeZone(java.util.TimeZone.getTimeZone("Zulu"));
		System.out.println(df.format(date));

	}
	
	public void testMD5() throws NoSuchAlgorithmException{
		String plaintext = "guest";
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(plaintext.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);
		// Now we need to zero pad it if you actually want the full 32 chars.
		while(hashtext.length() < 32 ){
		  hashtext = "0"+hashtext;
		}
		System.out.println(hashtext);
	}
}
