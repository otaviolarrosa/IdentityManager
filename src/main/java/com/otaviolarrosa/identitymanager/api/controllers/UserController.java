package com.otaviolarrosa.identitymanager.api.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.otaviolarrosa.identitymanager.api.commons.ApiResultBase;
import com.otaviolarrosa.identitymanager.application.user.create.CreateUserInput;
import com.otaviolarrosa.identitymanager.application.user.create.CreateUserResult;
import com.otaviolarrosa.identitymanager.application.user.create.CreateUserService;
import com.otaviolarrosa.identitymanager.application.user.get.GetUserResult;
import com.otaviolarrosa.identitymanager.application.user.get.GetUserService;

@RestController
public class UserController {
	
	@Autowired
	private CreateUserService createUserService;
	
	@Autowired
	private GetUserService getUserService;

	@PostMapping("/api/v1/user")
	ResponseEntity<ApiResultBase> createUser(@RequestBody CreateUserInput createUserInput) {
		CreateUserResult result = new CreateUserResult();		
		try {
			result = createUserService.handleExecution(createUserInput);
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
	
	@GetMapping("/api/v1/user/{userCode}")
	ResponseEntity<ApiResultBase> createUser(@PathVariable("userCode") UUID userCode) {
		ApiResultBase result = new GetUserResult();		
		try {
			result = getUserService.handleExecution(userCode);
			if(result.getIsValid()) {
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
