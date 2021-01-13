package com.login.dao;


import org.springframework.data.repository.CrudRepository;

import com.login.model.User;
public interface UserRepository  extends CrudRepository<User, String>{
	
}