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

public class ParticipantTest {

	private Validator validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();

	}

	@Test
	public void shouldNotAcceptShortName() {
		Participant participant = new Participant();
		participant.setName("A");
		
		
		Set<ConstraintViolation<Participant>> restrictions = validator.validate(participant);
		assertThat(restrictions, Matchers.hasSize(1));
	}
	
	@Test
	public void shouldNotAcceptNullUser() {
		Participant participant = new Participant();
		
		Set<ConstraintViolation<Participant>> restrictions = validator.validate(participant);
		assertThat(restrictions, Matchers.hasSize(1));
	}
	
	@Test
	public void shouldNotAcceptNegativeWinCount() {
		Participant participant = new Participant();
		participant.setWinCount(-1);
		
		Set<ConstraintViolation<Participant>> restrictions = validator.validate(participant);
		assertThat(restrictions, Matchers.hasSize(2));
	}
	
	
	@Test
	public void shouldNotAcceptNegativeMatchCount() {
		Participant participant = new Participant();
		participant.setMatchCount(-1);
		
		Set<ConstraintViolation<Participant>> restrictions = validator.validate(participant);
		assertThat(restrictions, Matchers.hasSize(2));
	}
	
	 @Test
	  public void shouldAcceptValidParticipantInformation() {
		 Participant participant = new Participant();
			participant.setName("SG");
			participant.setMatchCount(1);
			participant.setWinCount(1);
			
			Set<ConstraintViolation<Participant>> restrictions = validator.validate(participant);
			assertThat(restrictions, Matchers.empty());
	  }
}


	

