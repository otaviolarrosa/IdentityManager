package com.otaviolarrosa.identitymanager.application.role.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otaviolarrosa.identitymanager.domain.utils.ObjectUtils;
import com.otaviolarrosa.identitymanager.domain.utils.StringUtils;
import com.otaviolarrosa.identitymanager.infrastructure.database.entities.RoleEntity;
import com.otaviolarrosa.identitymanager.infrastructure.database.repositories.RoleRepository;

@Service
public class CreateRoleValidator {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public CreateRoleResult validate(CreateRoleInput input) {
		CreateRoleResult result = new CreateRoleResult();
		
		//roleName
		String roleName = input.getRoleName();
		if(StringUtils.StringIsNullOrEmptyOrWhitespace(roleName)) {
			result.AddError("roleName contains invalid value.");
		}
		
		if(!StringUtils.HasMinimumLenght(roleName, 3)) {
			result.AddError("roleName contains invalid value.");
		}
		
		if(!StringUtils.HasMaximumLenght(roleName, 50)) {
			result.AddError("roleName contains invalid value.");
		}
		
		//roleCode
		String roleCode = input.getRoleCode();
		if(StringUtils.StringIsNullOrEmptyOrWhitespace(roleCode)) {
			result.AddError("roleCode contains invalid value.");
		}
		
		RoleEntity roleEntityByCode = roleRepository.findByRoleCode(roleCode);
		if(ObjectUtils.isNotNull(roleEntityByCode)) {
			result.AddError(String.format("Role with code %s already registered.", roleCode));
		}
		
		if(!StringUtils.HasMinimumLenght(roleCode, 3)) {
			result.AddError("roleCode contains invalid value.");
		}
		
		if(!StringUtils.HasMaximumLenght(roleCode, 10)) {
			result.AddError("roleCode contains invalid value.");
		}
		
		return result;
	}
}
