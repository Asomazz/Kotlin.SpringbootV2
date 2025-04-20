package com.coded.spring.ordering.users

import com.coded.spring.com.coded.spring.ordering.authentication.jwt.JwtService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController(
    private val usersRepository: UserRepository,
    private val jwtService: JwtService,
) {
    @PostMapping("/user")
    fun register(@RequestBody request: UserRequest) {
        val newUser = UserEntity(
            username = request.username,
            password = request.password
        )
        usersRepository.save(newUser)

    }

    @PostMapping("/auth/login1")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<String> {
        val user = usersRepository.findByUsername(request.username)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found")

        if (user.password != request.password) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password")
        }
        val token = jwtService.generateToken(username = request.username)
        return ResponseEntity.ok("Login successful for user: ${user.username}. token=${token}")
    }

}

data class UserRequest(
    val username: String,
    val password: String,
)

data class LoginRequest(
    val username: String,
    val password: String
)
