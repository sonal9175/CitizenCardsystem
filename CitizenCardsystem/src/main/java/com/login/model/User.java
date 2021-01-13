package com.login.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="user_table")
public class User {
	
	public static final String persist = null;

	@Id
	@NotEmpty(message = "Please enter the name")
	@Email(message = "Invalid Email")
	private String uemailid;
	
	@NotEmpty(message = "Please provide a name")
	private String upass;
	
	
	
	public User() {
		super();
	}
	
	public User(String uemailid, String upass) {
		super();
		this.uemailid = uemailid;
		this.upass = upass;
		
	}
	public String getUemailid() {
		return uemailid;
	}
	public void setUemailid(String uemailid) {
		this.uemailid = uemailid;
	}
	
	
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}

	@Override
	public String toString() {
		return "User [uemailid=" + uemailid + ", upass=" + upass + "]";
	}

	


	
}
