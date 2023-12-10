package com.otaviolarrosa.identitymanager.user.create;

import org.springframework.stereotype.Service;

import com.otaviolarrosa.identitymanager.commons.api.ApiResultBase;
import com.otaviolarrosa.identitymanager.commons.primitives.EmailUtils;
import com.otaviolarrosa.identitymanager.commons.primitives.StringUtils;

@Service
public class CreateUserValidator {
	public ApiResultBase validate(CreateUserInput input) {
		CreateUserResult result = new CreateUserResult();
		
		//firstName
		String firstName = input.getFirstName();
		if(StringUtils.StringIsNullOrEmptyOrWhitespace(firstName)) {
			result.AddError("firstName contains invalid value.");
		}
		
		int firstNameMinimumLenght = 3;
		if(!StringUtils.HasMinimumLenght(firstName, firstNameMinimumLenght)) {
			result.AddError(String.format("%s didn't have the minimum lenght: %s.", firstName, firstNameMinimumLenght));
		}
		
		//lastName
		String lastName = input.getLastName();
		if(StringUtils.StringIsNullOrEmptyOrWhitespace(lastName)) {
			result.AddError("lastName contains invalid value.");
		}
		
		int lastNameMinimumLenght = 3;
		if(!StringUtils.HasMinimumLenght(lastName, lastNameMinimumLenght)) {
			result.AddError(String.format("%s didn't have the minimum lenght: %s.", lastName, lastNameMinimumLenght));
		}
		
		//email
		String email = input.getEmail();
		if(StringUtils.StringIsNullOrEmptyOrWhitespace(email)) {
			result.AddError("password contains invalid value.");
		}
		
		if(!EmailUtils.isValid(email)) {
			result.AddError(String.format("email have invalid format."));
		}
		
		
		//password
		String password = input.getPassword();
		if(StringUtils.StringIsNullOrEmptyOrWhitespace(password)) {
			result.AddError("password contains invalid value.");
		}
		
		int passwordMinimumLenght = 8;
		if(!StringUtils.HasMinimumLenght(password, passwordMinimumLenght)) {
			result.AddError(String.format("password didn't have the minimum lenght: %s.", lastNameMinimumLenght));
		}
		
		return result;
	}
}
