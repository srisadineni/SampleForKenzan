package com.demo.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.demo.domain.Dog;
import com.demo.util.PayloadValidator;

public class PayloadValidatorTest {

	@Test
	public void validatePayLoad() {
		Dog dog = new Dog(1, "Pug", null);
		assertEquals(false, PayloadValidator.validateCreatePayload(dog));
	}
	
	@Test
	public void validateInvalidPayLoad() {
		Dog dog = new Dog(0, "Pug", null);
		assertEquals(true, PayloadValidator.validateCreatePayload(dog));
	}
	
	

}
