package com.coded.spring.com.coded.spring.ordering.users


import com.coded.spring.com.coded.spring.ordering.authentication.jwt.JwtService
import com.coded.spring.ordering.users.AuthenticationRequest
import com.coded.spring.ordering.users.AuthenticationResponse
import com.coded.spring.ordering.users.UserEntity
import com.coded.spring.ordering.users.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userDetailsService : UserDetailsService,
    private val passwordEncoder : PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService
) {
    fun register(authRequest: AuthenticationRequest): AuthenticationResponse {

        if (authRequest.username.isBlank()){
            throw IllegalArgumentException("Username must not be blank!")
        }

        if (authRequest.password.length < 8) {
            throw IllegalArgumentException("Password must be at least 8 characters long")
        }

        val newUser = UserEntity(
            username = authRequest.username,
            password = passwordEncoder.encode(authRequest.password)
        )
        userRepository.save(newUser)

        val authToken = UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password)
        val authentication = authenticationManager.authenticate(authToken)

        if (authentication.isAuthenticated) {
            val userDetails = userDetailsService.loadUserByUsername(authRequest.username)
            val token = jwtService.generateToken(userDetails.username)
            return AuthenticationResponse(token)
        } else {
            throw BadCredentialsException("Could not authenticate user!")
        }
    }

    fun login(authRequest: AuthenticationRequest): AuthenticationResponse {
        val authToken = UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password)
        val authentication = authenticationManager.authenticate(authToken)

        if (authentication.isAuthenticated) {
            val userDetails = userDetailsService.loadUserByUsername(authRequest.username)
            val token = jwtService.generateToken(userDetails.username)
            return AuthenticationResponse(token)
        } else {
            throw BadCredentialsException("Invalid credentials!")
        }
    }
}