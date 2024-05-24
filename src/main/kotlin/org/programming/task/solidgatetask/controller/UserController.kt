package org.programming.task.solidgatetask.controller

import org.programming.task.solidgatetask.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class UserController(
    private val userService: UserService
) {
    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @PutMapping("/set-users-balance")
    @ResponseStatus(HttpStatus.OK)
    fun setUserBalances(@RequestBody balances: Map<Int, Int>) {
        logger.info("Received request $balances in /set-users-balance")
        val updatedUsers = userService.updateUsersBalance(balances)
        logger.info("Return response $updatedUsers in /set-users-balance")
    }
}