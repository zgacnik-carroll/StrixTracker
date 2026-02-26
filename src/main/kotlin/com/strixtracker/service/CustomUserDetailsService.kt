package com.strixtracker.service

import com.strixtracker.repository.UserRepository
import org.springframework.security.core.userdetails.*
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val repo: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = repo.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found")

        return org.springframework.security.core.userdetails.User
            .withUsername(user.username)
            .password(user.password)
            .roles(user.role)
            .build()
    }
}