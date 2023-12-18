package com.otaviolarrosa.singleSignOn.application.commons.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

class EmailUtils {
    companion object{
        private val expression = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"
        private val compiledPattern: Pattern = Pattern.compile(expression)

        fun isValid(email: String?): Boolean {
            val matcher: Matcher = compiledPattern.matcher(email)
            return matcher.matches()
        }
    }
}