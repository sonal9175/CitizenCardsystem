package com.login.dao;


import org.springframework.data.repository.CrudRepository;

import com.login.model.Admin;


public interface AdminRepository  extends CrudRepository<Admin, String>{
	
}