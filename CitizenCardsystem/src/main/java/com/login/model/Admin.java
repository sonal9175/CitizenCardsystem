package com.login.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="admin_Table")
public class Admin {
	
	public static final String persist = null;

	@Id
	@NotEmpty(message = "Please enter the admin user name")
	private String adminUserName;
	
	@NotEmpty(message = "Please provide a admin password")
	private String adminPassword;

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public static String getPersist() {
		return persist;
	}

	public Admin(@NotEmpty(message = "Please enter the name") String adminUserName,
			@NotEmpty(message = "Please provide a name") String adminPassword) {
		super();
		this.adminUserName = adminUserName;
		this.adminPassword = adminPassword;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
