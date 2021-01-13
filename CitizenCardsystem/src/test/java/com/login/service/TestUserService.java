package com.login.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import com.login.dao.UserRepository;
import com.login.model.User;


@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class TestUserService {
	@Autowired
	
	private UserRepository repository;
	
	@Test
	@Rollback(false)
	@Order(1)
	public void testSaveUser() {
		User SaveUser = repository.save(new User("Sonal@gmail.com", "Sonal"));
		
		assertThat(SaveUser.getUemailid()).isEqualTo("Sonal@gmail.com");
	}
	

	
	@Test
	@Rollback(false)
	@Order(2)
	public void testUpdateUser() {
		User user = repository.findById("Sonal@gmail.com").get();	
		user.setUpass("Slk");
		
		repository.save(user);
		
		User updatedUser = repository.findById("Sonal@gmail.com").get();
		
		assertThat(updatedUser.getUpass()).isEqualTo("Slk");
	}
	
	@Test
	@Rollback(false)
	@Order(3)
	public void testcheckPassword() {
		User user = repository.findById("Sonal@gmail.com").get();	
	
		assertThat(user.getUpass()).isEqualTo("Slk");
	}


}
