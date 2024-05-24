package org.programming.task.solidgatetask.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.programming.task.solidgatetask.model.UserEntity
import org.programming.task.solidgatetask.repository.UserRepository

class UserServiceTest {

    @Test
    fun should_updateUsers_when_inputMapIsNotEmpty() {
        val userRepository = mock(UserRepository::class.java)
        val userService = UserService(userRepository)
        val balances = mapOf(1 to 100, 2 to 200)
        val user1 = UserEntity(1).apply { balance = 50 }
        val user2 = UserEntity(2).apply { balance = 150 }

        `when`(userRepository.findAllById(balances.keys)).thenReturn(listOf(user1, user2))
        doAnswer { invocation ->
            val users = invocation.getArgument(0) as List<UserEntity>
            assertEquals(2, users.size)
            assertEquals(100, users.find { it.id == 1 }?.balance)
            assertEquals(200, users.find { it.id == 2 }?.balance)
            null
        }.`when`(userRepository).saveAll(anyList())

        userService.updateUsersBalance(balances)

        verify(userRepository).findAllById(balances.keys)
        verify(userRepository).saveAll(anyList())
    }

    @Test
    fun should_updateNothing_when_inputMapIsEmpty() {
        val userRepository = mock(UserRepository::class.java)
        val userService = UserService(userRepository)
        val emptyBalances = emptyMap<Int, Int>()

        userService.updateUsersBalance(emptyBalances)

        verify(userRepository, never()).saveAll(anyList())
    }
}