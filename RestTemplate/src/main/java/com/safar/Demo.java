package com.safar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo {

	public static void main(String[] args) {
		ApplicationContext ap = new AnnotationConfigApplicationContext(AppConfig.class);
		RestClientBean rs = ap.getBean("restClientBean",RestClientBean.class);
		
		rs.CallingApi();
	}
}
