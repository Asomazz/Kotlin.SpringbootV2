package com.coded.spring.ordering.profiles

import com.coded.spring.ordering.users.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "profiles")
data class ProfileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val firstName: String,

    val lastName: String,

    val phoneNumber: String,

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user:UserEntity? = null,

) { constructor() : this(
id = 0,
firstName = "",
lastName = "",
phoneNumber = "",

) }

