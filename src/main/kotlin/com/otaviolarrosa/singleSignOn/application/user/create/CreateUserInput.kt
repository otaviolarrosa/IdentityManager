package com.otaviolarrosa.singleSignOn.application.user.create

import com.otaviolarrosa.singleSignOn.application.commons.utils.StringUtils

class CreateUserInput {
    var firstName: String = StringUtils.STRING_EMPTY
    var lastName: String = StringUtils.STRING_EMPTY
    var email: String = StringUtils.STRING_EMPTY
    var password: String = StringUtils.STRING_EMPTY
}