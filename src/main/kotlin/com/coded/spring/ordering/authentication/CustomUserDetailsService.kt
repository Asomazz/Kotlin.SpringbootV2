package com.coded.spring.com.coded.spring.ordering.authentication

import com.coded.spring.ordering.users.UserRepository
import org.springframework.security.core.userdetails.*
import org.springframework.stereotype.Service


@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
       val user = userRepository.findByUsername(username)
           ?: throw UsernameNotFoundException("User not found!")

        return User.builder()
            .username(user.username)
            .password(user.password)
            .roles(user.role.toString())
            .build()
    }
}

