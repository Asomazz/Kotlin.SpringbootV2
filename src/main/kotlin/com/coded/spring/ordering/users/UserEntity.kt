package com.coded.spring.ordering.users

import com.coded.spring.ordering.profiles.ProfileEntity
import jakarta.persistence.*


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

    @OneToOne(mappedBy = "user")
    val profile: ProfileEntity? = null


) {
    constructor() : this(null, "", "", "",Roles.USER, ProfileEntity())
}

enum class Roles {
    USER, ADMIN
}
