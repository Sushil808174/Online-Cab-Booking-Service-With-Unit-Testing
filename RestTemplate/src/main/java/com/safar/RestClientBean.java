package com.safar;

import java.util.Base64;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClientBean {

	public void CallingApi() {
		RestTemplate restTemplate = new RestTemplate();
		String authStr = "raman@gmail.com:1234";
		String bas64Cred = Base64.getEncoder().encodeToString(authStr.getBytes());
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization","Basic "+bas64Cred);
		
		HttpEntity<String> request = new HttpEntity<String>(httpHeaders);
		
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8888/signIn",HttpMethod.GET,request,String.class);
		
		String result = response.getBody();
		System.out.println(result);
		
//		getting header
		String token = response.getHeaders().getFirst("Authorization");
		System.out.println("Token is "+token);
		
		
		//creating another headers object to call another api
		HttpHeaders headers2 = new HttpHeaders();
		//attaching the jwt token to the another protected api call
		headers2.add("Authorization", "Bearer " + token);
		// shortcut approach
//		headers2.setBearerAuth(token);
		
		HttpEntity<String> he = new HttpEntity<String>(headers2);

		
		//		first way
		
//		ResponseEntity<List> re2= restTemplate.exchange("http://localhost:8888/users",HttpMethod.GET ,he,List.class);
		
//		second way
		
		ResponseEntity<List<Users>> list = restTemplate.exchange("http://localhost:8888/users",HttpMethod.GET,he, new ParameterizedTypeReference<List<Users>> () {});
		
		
		
		System.out.println(list.getBody());
	}
	
}
