package com.otaviolarrosa.identitymanager.application.role.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otaviolarrosa.identitymanager.infrastructure.database.entities.RoleEntity;
import com.otaviolarrosa.identitymanager.infrastructure.database.repositories.RoleRepository;

@Service
public class CreateRoleService {
	
	@Autowired
	private CreateRoleValidator validator;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public CreateRoleResult handleExecution(CreateRoleInput input) {
		CreateRoleResult result = new CreateRoleResult();
		result = validator.validate(input);
		if(!result.isValid()) {
			return result;
		}
		
		RoleEntity entity = new RoleEntity();
		entity.setRoleName(input.getRoleName());
		entity.setRoleCode(input.getRoleCode());
		
		roleRepository.save(entity);
		
		return result;
	}
}
