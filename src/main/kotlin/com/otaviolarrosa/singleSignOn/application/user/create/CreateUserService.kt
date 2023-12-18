package com.otaviolarrosa.singleSignOn.application.user.create

import com.otaviolarrosa.singleSignOn.infrastructure.database.entities.UserEntity
import com.otaviolarrosa.singleSignOn.infrastructure.database.repositories.UserRepository
import com.otaviolarrosa.singleSignOn.infrastructure.database.security.Encrytptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CreateUserService @Autowired constructor(
    private val userRepository: UserRepository,
    private val validator: CreateUserValidator,
    private val encrytptor: Encrytptor
){

    fun handleExecution(input: CreateUserInput) : CreateUserResult {
        val result: CreateUserResult = validator.validate(input)

        if(!result.valid){
            return result
        }

        val entity = UserEntity()
        entity.firstName = input.firstName
        entity.lastName = input.lastName
        entity.email = input.email
        entity.password =  encrytptor.encrypt(input.password)
        val userCode = UUID.randomUUID()
        entity.userCode = userCode
        result.userCode = userCode

        userRepository.save(entity)
        return result
    }
}