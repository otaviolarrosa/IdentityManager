package com.otaviolarrosa.identitymanager.user.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.otaviolarrosa.identitymanager.commons.api.ApiResultBase;

@RestController
public class UserController {
	
	@Autowired
	private CreateUserService service;

	@PostMapping("/api/v1/user")
	ResponseEntity<ApiResultBase> createUser(@RequestBody CreateUserInput createUserInput) {
		ApiResultBase result = new CreateUserResult();		
		try {
			result = service.handleExecution(createUserInput);
			if(result.getIsValid()) {
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
