package com.login.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.login.dao.AdminRepository;
import com.login.dao.UserRepository;
import com.login.error.NoSuchElementException;
import com.login.model.Admin;
import com.login.model.User;
import com.login.service.IAdminService;
import com.login.service.IUserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired(required = true)
	public IUserService userservice;

	@Autowired
	private UserRepository repository;

	@Autowired(required = true)
	public IAdminService adminservice;

	@Autowired
	private AdminRepository adminRepository;

	// User controller part
	// to get user by email id
	@GetMapping("/user/get/{uemailid}")
	public ResponseEntity<User> getUserById(@PathVariable String uemailid) throws NoSuchElementException {
		User user = repository.findById(uemailid)
				.orElseThrow(() -> new NoSuchElementException("User id for email id " + uemailid + " not found"));
		return ResponseEntity.ok().body(user);
	}

	// for user registration
	@PostMapping("/user/register")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> signInUser(@Valid @RequestBody User newUser) {
		User user = userservice.saveUser(newUser);
		String uemailid = user.getUemailid();
		return new ResponseEntity<String>("User With ID :" + uemailid + " registered Successfully ", HttpStatus.OK);

	}

	// for user login
	@GetMapping(value = "/user/login/{uemailid}/{upass}")
	public ResponseEntity<String> loginUser(@PathVariable String uemailid, @PathVariable String upass) {
		User user = userservice.checkPassword(uemailid, upass);
		if (user != null) {
			return new ResponseEntity<String>("Login Successful", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Login unsuccessful! Please check the password.", HttpStatus.OK);
	}

	// For user forget password
	@PutMapping("/user/forgetPassword/{uemailid}")
	public ResponseEntity<User> updateUserPassword(@PathVariable String uemailid, @Valid @RequestBody User userDetails)
			throws NoSuchElementException {
		User user = repository.findById(uemailid)
				.orElseThrow(() -> new NoSuchElementException("User not found for this id:" + uemailid));

		user.setUemailid(userDetails.getUemailid());
		user.setUpass(userDetails.getUpass());

		final User updatedUser = repository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	// Admin controller part
	// to get admin by username
	@GetMapping("admin/{adminUserName}")
	public ResponseEntity<Admin> getAdminById(@PathVariable String adminUserName) throws NoSuchElementException {
		Admin admin = adminRepository.findById(adminUserName)
				.orElseThrow(() -> new NoSuchElementException("Admin username " + adminUserName + " not found"));
		return ResponseEntity.ok().body(admin);
	}

	// for admin registration
	@PostMapping("/admin")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> signInAdmin(@Valid @RequestBody Admin newAdmin) {
		Admin admin = adminservice.saveAdmin(newAdmin);
		String adminUserName = admin.getAdminUserName();
		return new ResponseEntity<String>("User With ID :" + adminUserName + " registered Successfully ",
				HttpStatus.OK);

	}

	// for admin login
	@GetMapping(value = "/admin/{adminUserName}/{adminPassword}")
	public ResponseEntity<String> loginAdmin(@PathVariable String adminUserName, @PathVariable String adminPassword) {
		Admin admin = adminservice.checkPassword(adminUserName, adminPassword);
		if (admin != null) {
			return new ResponseEntity<String>("Login Successful", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Login unsuccessful! Please check the password.", HttpStatus.OK);
	}

	// For admin forget password
	@PutMapping("/admin/{adminUserName}")
	public ResponseEntity<Admin> updateAdminPassword(@PathVariable String adminUserName,
			@Valid @RequestBody Admin adminDetails) throws NoSuchElementException {
		Admin admin = adminRepository.findById(adminUserName)
				.orElseThrow(() -> new NoSuchElementException("Admin not found for this username:" + adminUserName));

		admin.setAdminUserName(adminDetails.getAdminUserName());
		admin.setAdminPassword(adminDetails.getAdminPassword());

		final Admin updatedAdmin = adminRepository.save(admin);
		return ResponseEntity.ok(updatedAdmin);
	}

}
