package com.strixtracker.controller

import com.strixtracker.service.OwlSightingService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.time.format.DateTimeFormatter

@Controller
@RequestMapping("/sightings")
class OwlSightingController(
    private val service: OwlSightingService
) {

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    @GetMapping
    fun listSightings(model: Model): String {
        // Map sightings to a simple data structure with formatted dateTime
        val sightings = service.getAllSightings().map { s ->
            mapOf(
                "species" to s.species,
                "location" to s.location,
                "dateTime" to s.dateTime.format(formatter),
                "notes" to s.notes
            )
        }
        model.addAttribute("sightings", sightings)
        return "sightings"
    }

    @GetMapping("/new")
    fun showForm(): String = "new-sighting"

    @PostMapping
    fun addSighting(
        @RequestParam species: String,
        @RequestParam location: String,
        @RequestParam notes: String
    ): String {
        val sighting = com.strixtracker.model.OwlSighting(
            species = species,
            location = location,
            dateTime = java.time.LocalDateTime.now(),
            notes = notes
        )

        service.addSighting(sighting)
        return "redirect:/sightings"
    }
}