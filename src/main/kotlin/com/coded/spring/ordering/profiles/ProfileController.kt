package com.coded.spring.ordering.profiles


import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProfileController(
    private val profileService: ProfileService
) {

    @PostMapping("/profile")
    fun createProfile(@RequestBody request: ProfileRequest, authentication: Authentication): ResponseEntity<String> {
        return profileService.createProfile(request, authentication.name)
    }
}