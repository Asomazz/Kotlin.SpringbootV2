package com.coded.spring.ordering.profiles

import com.coded.spring.ordering.users.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileRepository : JpaRepository<ProfileEntity, Long> {
    fun existsByUser(user: UserEntity): Boolean
}
