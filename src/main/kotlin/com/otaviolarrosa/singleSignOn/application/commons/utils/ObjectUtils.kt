package com.otaviolarrosa.singleSignOn.application.commons.utils

object ObjectUtils {
    fun isNull(obj: Any?): Boolean {
        return obj == null
    }

    fun isNotNull(obj: Any?): Boolean {
        return !isNull(obj)
    }
}