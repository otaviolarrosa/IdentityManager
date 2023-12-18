package com.otaviolarrosa.singleSignOn.application.user.create

import com.otaviolarrosa.singleSignOn.api.commons.ApiResultBase
import java.util.*

class CreateUserResult : ApiResultBase() {
    var userCode: UUID? = null
}