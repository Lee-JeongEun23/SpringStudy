package com.aopEx;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		Animals animal = context.getBean("animals", Animals.class);
		animal.getAnimalsInfo();

	}

}
