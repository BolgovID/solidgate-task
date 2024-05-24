package org.programming.task.solidgatetask.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    val id: Int,
) {
    var name: String? = null
    var balance: Int? = null
}