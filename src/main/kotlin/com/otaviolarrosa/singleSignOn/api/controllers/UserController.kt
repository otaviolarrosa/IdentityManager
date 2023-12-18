package com.otaviolarrosa.singleSignOn.api.controllers

import com.otaviolarrosa.singleSignOn.api.commons.ApiResultBase
import com.otaviolarrosa.singleSignOn.application.user.create.CreateUserInput
import com.otaviolarrosa.singleSignOn.application.user.create.CreateUserResult
import com.otaviolarrosa.singleSignOn.application.user.create.CreateUserService
import com.otaviolarrosa.singleSignOn.application.user.get.GetUserInput
import com.otaviolarrosa.singleSignOn.application.user.get.GetUserResult
import com.otaviolarrosa.singleSignOn.application.user.get.GetUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID


@RestController
class UserController @Autowired constructor(
    private val createUserService: CreateUserService,
    private val getUserService: GetUserService
) {

    @PostMapping("/api/v1/user")
    fun createUser(@RequestBody createUserInput: CreateUserInput?): ResponseEntity<ApiResultBase> {
        var result: CreateUserResult
        try {
            result = createUserService.handleExecution(createUserInput!!)
            return if (result.valid) {
                ResponseEntity<ApiResultBase>(result, HttpStatus.OK)
            } else {
                ResponseEntity<ApiResultBase>(result, HttpStatus.BAD_REQUEST)
            }
        } catch (ex: Exception) {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/api/v1/user/{userCode}")
    fun getUser(@PathVariable("userCode") userCode: UUID?): ResponseEntity<ApiResultBase> {
        var result: GetUserResult
        try {
            result = getUserService.handleExecution(userCode)
            return if (result.valid) {
                ResponseEntity<ApiResultBase>(result, HttpStatus.OK)
            } else {
                ResponseEntity<ApiResultBase>(result, HttpStatus.BAD_REQUEST)
            }
        } catch (ex: Exception) {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}