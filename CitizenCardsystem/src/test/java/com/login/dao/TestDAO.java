package com.login.dao;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import com.login.dao.UserRepository;
import com.login.model.User;

@DataJpaTest
@RunWith(SpringRunner.class)

public class TestDAO {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository repository;

	@BeforeEach
	public void setUp() {
		entityManager.persist(new User("Sonal@gmail.com", "Sonal"));
		entityManager.persist(new User("Kaminwar@gail.com", "Kaminwar"));
		entityManager.persist(new User("slk@gmail.com", "slk"));

	}

	@Test
	public void testSaveUser() {

		User user = repository.findById("Sonal@gmail.com").get();
		assertNotNull(user);
		assertThat(user.getUemailid()).isEqualTo("Sonal@gmail.com");

	}
	
	

}
