package com.otaviolarrosa.identitymanager.application.user.create;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otaviolarrosa.identitymanager.infrastructure.database.entities.UserEntity;
import com.otaviolarrosa.identitymanager.infrastructure.database.repositories.UserRepository;
import com.otaviolarrosa.identitymanager.infrastructure.encryption.Encryption;

@Service
public class CreateUserService {
	
	@Autowired
	private CreateUserValidator validator;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Encryption encryptor;
	
	public CreateUserResult handleExecution(CreateUserInput createUserInput) {
		CreateUserResult result = new CreateUserResult();
		result = validator.validate(createUserInput);
		if(!result.isValid()) {
			return result;
		}
		
		UserEntity entity = new UserEntity();
		entity.setEmail(createUserInput.getEmail());
		entity.setFirstName(createUserInput.getFirstName());
		entity.setLastName(createUserInput.getLastName());
				
		String password = encryptor.encrypt(createUserInput.getPassword());
		entity.setPassword(password);
		
		UUID userCode = java.util.UUID.randomUUID();
		entity.setUserCode(userCode);
		
		userRepository.save(entity);
		result.setUserCode(userCode);
		return result;
	}
}
