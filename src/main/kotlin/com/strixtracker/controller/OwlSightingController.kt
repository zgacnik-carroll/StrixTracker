package com.strixtracker.controller

import com.strixtracker.model.OwlSighting
import com.strixtracker.repository.UserRepository
import com.strixtracker.service.OwlSightingService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Controller
@RequestMapping("/sightings")
class OwlSightingController(
    private val service: OwlSightingService,
    private val userRepository: UserRepository
) {

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    // List sightings + species counts
    @GetMapping
    fun listSightings(model: Model): String {
        val auth = SecurityContextHolder.getContext().authentication
        val username = auth?.name ?: throw IllegalStateException("No logged-in user")

        val sightings = service.getSightingsForUser(username).map { s ->
            mapOf(
                "species" to s.species,
                "location" to s.location,
                "dateTime" to s.dateTime.format(formatter),
                "notes" to s.notes
            )
        }

        val speciesCounts = service.getSpeciesCountsForUser(username)

        model.addAttribute("sightings", sightings)
        model.addAttribute("speciesCounts", speciesCounts)
        return "sightings"
    }

    // Show form to add new sighting
    @GetMapping("/new")
    fun showForm(): String = "new-sighting"

    // Handle form submission
    @PostMapping
    fun addSighting(
        @RequestParam species: String,
        @RequestParam location: String,
        @RequestParam notes: String
    ): String {
        val auth = SecurityContextHolder.getContext().authentication
        val username = auth?.name ?: throw IllegalStateException("No logged-in user")
        val user = userRepository.findByUsername(username)!!

        val sighting = OwlSighting(
            species = species,
            location = location,
            dateTime = LocalDateTime.now(),
            notes = notes,
            user = user
        )

        service.addSighting(sighting)
        return "redirect:/sightings"
    }
}