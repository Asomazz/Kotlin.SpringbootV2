package com.coded.spring.ordering.profiles

import com.coded.spring.ordering.users.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ProfileService(
    private val userRepository: UserRepository,
    private val profileRepository: ProfileRepository
) {

    fun createProfile(request: ProfileRequest, username: String): ResponseEntity<String> {
        val user = userRepository.findByUsername(username)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found")

        if (profileRepository.existsByUser(user)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Profile already exists")
        }

        val userId = user.id

        val profile = ProfileEntity(
            firstName = request.firstName,
            lastName = request.lastName,
            phoneNumber = request.phoneNumber
        )

        profileRepository.save(profile)
        return ResponseEntity.ok("Profile created successfully")
    }
}