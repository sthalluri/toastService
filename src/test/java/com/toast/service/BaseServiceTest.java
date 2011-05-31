package com.toast.service;

import org.junit.Before;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class BaseServiceTest {
	protected BeanFactory factory;
	protected ClassPathXmlApplicationContext appContext;
	protected Response response;

	@Before
	public void setup(){
		appContext = new ClassPathXmlApplicationContext(
		        new String[] {"applicationContext.xml"});
		factory = (BeanFactory) appContext;
		response = (Response) factory.getBean("response");
	}	
}


