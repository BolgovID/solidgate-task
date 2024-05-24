package org.programming.task.solidgatetask.repository

import org.programming.task.solidgatetask.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Int>