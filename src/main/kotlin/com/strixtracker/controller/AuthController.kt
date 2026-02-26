package com.strixtracker.controller

import com.strixtracker.model.User
import com.strixtracker.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class AuthController(
    private val repo: UserRepository,
    private val encoder: PasswordEncoder
) {

    @GetMapping("/login")
    fun loginPage(): String = "login"

    @GetMapping("/register")
    fun registerPage(): String = "register"

    @PostMapping("/register")
    fun register(
        @RequestParam("username") username: String?,
        @RequestParam("password") password: String?
    ): String {

        // Handle missing username/password safely
        val cleanUsername = username?.trim() ?: return "redirect:/register?error"
        val cleanPassword = password?.trim() ?: return "redirect:/register?error"

        // Prevent duplicate usernames
        if (repo.findByUsername(cleanUsername) != null) {
            return "redirect:/register?error"
        }

        val user = User(
            username = cleanUsername,
            password = encoder.encode(cleanPassword)
        )

        repo.save(user)

        return "redirect:/login"
    }
}