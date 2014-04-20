package com.study.platform.test;

import javax.ws.rs.core.Application;

import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class UserServiceTest {

	@Test
	public void test() {
		FileSystemXmlApplicationContext application = new FileSystemXmlApplicationContext("D:\\Java\\MyEclipse_10\\Study_And_Communication_Platform\\WebRoot\\WEB-INF\\applicationContext.xml");
		
	}

	

}
