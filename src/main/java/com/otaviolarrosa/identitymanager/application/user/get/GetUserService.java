package com.otaviolarrosa.identitymanager.application.user.get;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otaviolarrosa.identitymanager.domain.utils.ObjectUtils;
import com.otaviolarrosa.identitymanager.infrastructure.database.entities.UserEntity;
import com.otaviolarrosa.identitymanager.infrastructure.database.repositories.UserRepository;

@Service
public class GetUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public GetUserResult handleExecution(UUID userCode) {
		GetUserResult result = new GetUserResult();
		UserEntity entity = userRepository.findByUserCode(userCode);
		if(ObjectUtils.isNull(entity)) {
			result.AddError(String.format("User with code %s could not be found.", userCode));
			return result;
		}
		
		result.setFirstName(entity.getFirstName());
		result.setLastName(entity.getLastName());
		result.setEmail(entity.getEmail());
		result.setUserCode(entity.getUserCode());
		
		return result;
	}
}
