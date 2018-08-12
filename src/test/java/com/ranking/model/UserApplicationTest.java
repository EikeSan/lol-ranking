package com.ranking.model;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.Set;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import com.ranking.model.UserApplication;

public class UserApplicationTest {
	private Validator validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();

	}

	@Test
	public void shouldNotAcceptShortUsernameAndPassword() {
		UserApplication userApplication = new UserApplication();
		userApplication.setUsername("A");
		userApplication.setPassword("12345");
		
		Set<ConstraintViolation<UserApplication>> restrictions = validator.validate(userApplication);
		assertThat(restrictions, Matchers.hasSize(2));
	}
	
	@Test
	public void shouldNotAcceptNullUser() {
		UserApplication userApplication = new UserApplication();
		
		Set<ConstraintViolation<UserApplication>> restrictions = validator.validate(userApplication);
		assertThat(restrictions, Matchers.hasSize(2));
	}
	
	 @Test
	  public void shouldAcceptUserValidInformation() {
		 UserApplication userApplication = new UserApplication();
		 userApplication.setUsername("user");
		 userApplication.setPassword("123456");
			
	 
	    Set<ConstraintViolation<UserApplication>> restricoes = validator.validate(userApplication);
	 
	    assertThat(restricoes, Matchers.empty());
	  }
}
