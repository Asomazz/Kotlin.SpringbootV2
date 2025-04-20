package com.coded.spring.ordering.users

import com.coded.spring.ordering.orders.OrderEntity
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String): UserEntity?
}

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String = "",

    var username: String,
    var password: String,

    @Enumerated(EnumType.STRING)
    val role: Roles = Roles.USER,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val orders: List<OrderEntity> = listOf()
//This lets the user "know about" their orders, but the actual foreign key exists in the orders table, not the users table. (only the child "the Many" holds the foreign key)
// this is saying: If I get a user from the database, and I want to see their orders, look in the orders table and find all the rows where user_id = this user’s id
){
    constructor() : this(null, "", "", "",Roles.USER, listOf())
}

enum class Roles {
    USER, ADMIN
}