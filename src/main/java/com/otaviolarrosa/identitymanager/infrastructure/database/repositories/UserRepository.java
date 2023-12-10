package com.otaviolarrosa.identitymanager.infrastructure.database.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otaviolarrosa.identitymanager.infrastructure.database.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> { 
	UserEntity findByUserCode(UUID userCode);
	UserEntity findByEmail(String email);
}
