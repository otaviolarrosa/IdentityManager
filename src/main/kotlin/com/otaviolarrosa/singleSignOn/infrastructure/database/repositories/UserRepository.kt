package com.otaviolarrosa.singleSignOn.infrastructure.database.repositories

import com.otaviolarrosa.singleSignOn.infrastructure.database.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UserEntity?, Long?> {
    fun findByUserCode(userCode: UUID?): UserEntity
    fun findByEmail(email: String?): UserEntity?
}
