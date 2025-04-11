package com.coded.spring.ordering.users

import com.coded.spring.ordering.orders.OrderEntity
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long>

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val name: String,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val orders: List<OrderEntity> = listOf()
//This lets the user "know about" their orders, but the actual foreign key exists in the orders table, not the users table. (only the child "the Many" holds the foreign key)

){
    constructor() : this(null, "", listOf())
}
