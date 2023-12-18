package com.otaviolarrosa.singleSignOn.api.commons

open class ApiResultBase {
    val errors: MutableList<Error> = mutableListOf()
    val valid  : Boolean
        get() = this.errors.isEmpty()
    val invalid  : Boolean
        get() = this.errors.isNotEmpty()

    fun addError(errorMessage: String) {
        val error = Error(errorMessage)
        errors.addLast(error)
    }
}