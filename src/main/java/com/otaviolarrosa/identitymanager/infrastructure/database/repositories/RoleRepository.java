package com.otaviolarrosa.identitymanager.infrastructure.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otaviolarrosa.identitymanager.infrastructure.database.entities.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	RoleEntity findByRoleCode(String roleCode);
}
