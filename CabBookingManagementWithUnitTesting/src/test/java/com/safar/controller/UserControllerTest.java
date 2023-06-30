package com.safar.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import com.safar.entity.Users;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "/application-test.properties")
public class UserControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	@DisplayName("Customer can be registered")
	public void testSaveCustomerHandler_whenValidDetailsProvided_returnSaveCustomer() throws Exception{
//		Arrange
		Users user = new Users();
		user.setUsername("Aman");
		user.setEmail("aman@gmail.com");
		user.setAddress("Kanpur");
		user.setPassword(passwordEncoder.encode("12345"));
		user.setPhone("9087654321");
		user.setRole("ADMIN");
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Users> entity = new HttpEntity<>(user,httpHeaders);
		
//		act
		ResponseEntity<Users> re = testRestTemplate.postForEntity("/users", entity, Users.class);
	
		Users createdUser = re.getBody();
		
//		assert
		assertEquals(HttpStatus.CREATED, re.getStatusCode(),"Created status code is not 201");
		assertNotNull(createdUser.getUserId(),"Register user should have id");
	}
}
