package com.demo.util;

import com.demo.domain.Dog;


public class PayloadValidator {
	
	public static boolean validateCreatePayload(Dog dog){
		if (dog.getId() > 0){
			return false;
		}
		return true;
	}

}
