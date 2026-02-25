package com.strixtracker.service

import com.strixtracker.model.OwlSighting
import com.strixtracker.repository.OwlSightingRepository
import org.springframework.stereotype.Service

@Service
class OwlSightingService(
    private val repository: OwlSightingRepository
) {

    /** Save a new owl sighting to the database */
    fun addSighting(sighting: OwlSighting) = repository.save(sighting)

    /** Retrieve all owl sightings */
    fun getAllSightings(): List<OwlSighting> = repository.findAll()
}