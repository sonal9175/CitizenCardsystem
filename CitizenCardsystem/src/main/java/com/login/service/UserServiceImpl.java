package com.login.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.dao.UserRepository;
import com.login.model.User;


@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired(required=true)
	private UserRepository repository;

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		User u= repository.save(user);
		return u;
	}

	@Override
	public void updateUserPassword(String uemailid) {
		// TODO Auto-generated method stub
		User user=(User)repository.findById(uemailid).get();
		
		user.setUpass("321");
		
	 repository.save(user);
	}
/*
	@Override
	public User getUser(String uemailid, String upass) {
		// TODO Auto-generated method stub
		return repository.findById(uemailid).get();
	}
*/
	@Override
	public User checkPassword(String uemailid, String upass) {
		User user=(User)repository.findById(uemailid).get();
		if(user.getUpass().equals(upass)) {
			return user;
		}
		return null;
	}

	@Override
	public User getUser(String uemailid) {
		// TODO Auto-generated method stub
		return repository.findById(uemailid).get();
	}
	
	

}
