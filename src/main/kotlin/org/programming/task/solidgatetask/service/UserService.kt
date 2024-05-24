package org.programming.task.solidgatetask.service

import jakarta.transaction.Transactional
import org.programming.task.solidgatetask.model.UserEntity
import org.programming.task.solidgatetask.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    @Value("\${app.batchSize}")
    private var batchSize = 1000

    private val logger = LoggerFactory.getLogger(UserService::class.java)


    @Transactional
    fun updateUsersBalance(balances: Map<Int, Int>) {
        logger.info("Updating user balances started")
        val users = userRepository.findAllById(balances.keys)
        val usersById = users.associateBy(UserEntity::id)

        val updatedUsers = balances.mapNotNull { (userId, amount) ->
            usersById[userId]?.also { it.balance = amount }
        }

        updatedUsers
            .chunked(batchSize)
            .forEach(userRepository::saveAll)

        logger.info("Updating user balances completed")
    }
}