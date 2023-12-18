package com.otaviolarrosa.singleSignOn.application.user.get

import com.otaviolarrosa.singleSignOn.api.commons.ApiResultBase
import com.otaviolarrosa.singleSignOn.application.commons.utils.ObjectUtils
import com.otaviolarrosa.singleSignOn.application.commons.utils.StringUtils
import com.otaviolarrosa.singleSignOn.infrastructure.database.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class GetUserService @Autowired constructor(
    val validator: GetUserValidator,
    val userRepository: UserRepository
){
    fun handleExecution(userCode: UUID?): GetUserResult {
        var result: GetUserResult = validator.validate(userCode)

        if(result.invalid) {
            return result
        }

        var entity = this.userRepository.findByUserCode(userCode)
        result.userCode = entity.userCode.toString()
        result.firstName = entity.firstName
        result.lastName = entity.lastName
        result.email = entity.email
        return result
    }
}

@Service
class GetUserValidator {
    fun validate(userCode: UUID?) : GetUserResult {
        val result = GetUserResult()

        if(ObjectUtils.isNull(userCode) || StringUtils.stringIsNullOrEmptyOrWhitespace(userCode.toString())){
            result.addError("userCode has invalid value.")
        }

        return result
    }
}

class GetUserResult : ApiResultBase() {
    var firstName: String? = StringUtils.STRING_EMPTY
    var lastName: String? = StringUtils.STRING_EMPTY
    var email: String? = StringUtils.STRING_EMPTY
    var userCode: String? = StringUtils.STRING_EMPTY
}