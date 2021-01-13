package com.login.service;

import com.login.model.User;

public interface IUserService {

	public User saveUser(User user);
	public void updateUserPassword(String uemailid);
	public User getUser(String uemailid);
	public User checkPassword(String uemailid, String upass);
	
}
