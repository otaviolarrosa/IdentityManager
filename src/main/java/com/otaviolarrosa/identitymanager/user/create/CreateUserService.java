package com.otaviolarrosa.identitymanager.user.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otaviolarrosa.identitymanager.commons.api.ApiResultBase;
import com.otaviolarrosa.identitymanager.commons.encryption.Encryption;

@Service
public class CreateUserService {
	
	@Autowired
	private CreateUserValidator validator;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Encryption encryptor;
	
	public ApiResultBase handleExecution(CreateUserInput createUserInput) {
		ApiResultBase result = new CreateUserResult();
		result = validator.validate(createUserInput);
		if(!result.getIsValid()) {
			return result;
		}
		
		UserEntity entity = new UserEntity();
		entity.setEmail(createUserInput.getEmail());
		entity.setFirstName(createUserInput.getFirstName());
		entity.setLastName(createUserInput.getLastName());
				
		String password = encryptor.encrypt(createUserInput.getPassword());
		entity.setPassword(password);
		
		userRepository.save(entity);
		
		return result;
	}
}
