package com.otaviolarrosa.singleSignOn.infrastructure.database.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
class ConfigUtility @Autowired constructor(
    val env: Environment
) {
    fun getProperty(pPropertyKey: String?): String? {
        return pPropertyKey?.let { env.getProperty(it) }
    }
}