package com.login.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.dao.AdminRepository;
import com.login.model.Admin;


@Service
public class AdminServiceImpl implements IAdminService {
	
	@Autowired(required=true)
	private AdminRepository repository;


	@Override
	public Admin saveAdmin(Admin admin) {
		Admin admindb= repository.save(admin);
		return admindb;
	}

	@Override
	public void updateAdminPassword(String adminUserName) {
Admin admin=(Admin)repository.findById(adminUserName).get();
		
		admin.setAdminPassword("321");
		
	 repository.save(admin);
		
	}

	@Override
	public Admin getAdmin(String adminUserName) {
		// TODO Auto-generated method stub
		return repository.findById(adminUserName).get();
	}

	@Override
	public Admin checkPassword(String adminUserName, String adminPassword) {
		Admin admin=(Admin)repository.findById(adminUserName).get();
		if(admin.getAdminPassword().equals(adminPassword)) {
			return admin;
		}
		return null;
	}
	
	

}
