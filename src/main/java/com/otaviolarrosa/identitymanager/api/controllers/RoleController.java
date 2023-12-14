package com.otaviolarrosa.identitymanager.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.otaviolarrosa.identitymanager.api.commons.ApiResultBase;
import com.otaviolarrosa.identitymanager.application.role.create.CreateRoleInput;
import com.otaviolarrosa.identitymanager.application.role.create.CreateRoleResult;
import com.otaviolarrosa.identitymanager.application.role.create.CreateRoleService;

@RestController
public class RoleController {
	
	@Autowired
	private CreateRoleService createRoleService;
	
	@PostMapping("/api/v1/role")
	ResponseEntity<ApiResultBase> createUser(@RequestBody CreateRoleInput createRoleInput) {
		CreateRoleResult result = new CreateRoleResult();		
		try {
			result = createRoleService.handleExecution(createRoleInput);
			if(result.isValid()) {
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
