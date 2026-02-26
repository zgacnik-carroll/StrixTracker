package com.strixtracker.controller

import com.strixtracker.repository.OwlSightingRepository
import com.strixtracker.repository.UserRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController(
    private val owlSightingRepository: OwlSightingRepository,
    private val userRepository: UserRepository
) {

    @GetMapping("/")
    fun home(model: Model): String {
        val totalSightings = owlSightingRepository.count()
        val totalSpecies = owlSightingRepository.findAll()
            .map { it.species }
            .distinct()
            .count()
        val totalUsers = userRepository.count()

        model.addAttribute("totalSightings", totalSightings)
        model.addAttribute("totalSpecies", totalSpecies)
        model.addAttribute("totalUsers", totalUsers)

        return "home"
    }
}