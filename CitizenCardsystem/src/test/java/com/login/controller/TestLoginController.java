package com.login.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.login.CitizencardsystemApplication;
import com.login.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CitizencardsystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestLoginController {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void testGetUserById() {
		User user = restTemplate.getForObject(getRootUrl() + "/user/get/Sanjay@gmail.com", User.class);
		System.out.println(user.getUemailid());
		assertNotNull(user);
	}

	//Forget Password testing in controller layer
	@Test
	public void testUpdateUserPassword() {
		String uemailid = "Sanjay@gmail.com";
		User user = restTemplate.getForObject(getRootUrl() + "/user/forgetPassword/" + uemailid, User.class);
		user.setUemailid("Sanjay@gmail.com");
		user.setUpass("Sonal");

		restTemplate.put(getRootUrl() + "/employees/" + uemailid, user);

		User updatedUser = restTemplate.getForObject(getRootUrl() + "/user/forgetPassword/" + uemailid, User.class);
		assertNotNull(updatedUser);
	}
	
	//SignIn part testing in controller layer
	@Test
	public void testSignInUser() {
		User user = new User();
		user.setUemailid("Sanjay@gmail.com");
		user.setUpass("Sonal");

		ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/user/register", user, User.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}
	
	//Login part testing in controller layer
	@Test
	public void testloginUser() {
		String uemailid = "Sanjay@gmail.com";
		User user = restTemplate.getForObject(getRootUrl() + "/user/login/Sanjay@gmail.com/Sonal" + uemailid, User.class);
		user.getUemailid();
		user.getUpass();
		
		
		User loginUser = restTemplate.getForObject(getRootUrl() + "/user/login/Sanjay@gmail.com/Sonal" + uemailid,
				User.class);
		assertNotNull(loginUser);
		// assertEquals(user, loginUser);
	}

}
