package com.otaviolarrosa.singleSignOn.application.commons.utils

object StringUtils {
    const val STRING_EMPTY = ""


    fun stringIsNullOrEmptyOrWhitespace(str: String?): Boolean {
        return (str == null || str.trim() == "" || str.trim() == " ")
    }

    fun hasMinimumLenght(str: String, minimunLenght: Int): Boolean {
        return str.length >= minimunLenght
    }

    fun hasMaximumLenght(str: String, minimunLenght: Int): Boolean {
        return str.length <= minimunLenght
    }
}