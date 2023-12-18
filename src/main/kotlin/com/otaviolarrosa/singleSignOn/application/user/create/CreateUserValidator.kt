package com.otaviolarrosa.singleSignOn.application.user.create

import com.otaviolarrosa.singleSignOn.application.commons.utils.EmailUtils
import com.otaviolarrosa.singleSignOn.application.commons.utils.ObjectUtils
import com.otaviolarrosa.singleSignOn.application.commons.utils.StringUtils
import com.otaviolarrosa.singleSignOn.infrastructure.database.entities.UserEntity
import com.otaviolarrosa.singleSignOn.infrastructure.database.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CreateUserValidator @Autowired constructor(
    private val userRepository: UserRepository
){
    fun validate(input: CreateUserInput) : CreateUserResult {
        var result = CreateUserResult()


        //firstName
        if (StringUtils.stringIsNullOrEmptyOrWhitespace(input.firstName)) {
            result.addError("firstName contains invalid value.")
        }

        val firstNameMinimumLenght = 3
        if (!StringUtils.hasMinimumLenght(input.firstName, firstNameMinimumLenght)) {
            result.addError("firstName didn't have the minimum lenght: $firstNameMinimumLenght")
        }


        //lastName
        val lastName: String = input.lastName
        if (StringUtils.stringIsNullOrEmptyOrWhitespace(lastName)) {
            result.addError("lastName contains invalid value.")
        }

        val lastNameMinimumLenght = 3
        if (!StringUtils.hasMinimumLenght(lastName, lastNameMinimumLenght)) {
            result.addError("lastName didn't have the minimum lenght: $lastNameMinimumLenght.")
        }

        //email
        val email: String = input.email
        if (StringUtils.stringIsNullOrEmptyOrWhitespace(email)) {
            result.addError("password contains invalid value.")
        }

        if (!EmailUtils.isValid(email)) {
            result.addError(String.format("email have invalid format."))
        }

        val userEntityByEmail: UserEntity? = this.userRepository.findByEmail(email)
        if (ObjectUtils.isNotNull(userEntityByEmail)) {
            result.addError("User with email $email already registered.")
        }


        //password
        val password: String = input.password
        if (StringUtils.stringIsNullOrEmptyOrWhitespace(password)) {
            result.addError("password contains invalid value.")
        }

        val passwordMinimumLenght = 8
        if (!StringUtils.hasMinimumLenght(password, passwordMinimumLenght)) {
            result.addError("password didn't have the minimum lenght: $passwordMinimumLenght.")
        }

        return result
    }
}