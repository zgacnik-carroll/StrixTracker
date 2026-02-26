package com.strixtracker.service

import com.strixtracker.model.OwlSighting
import com.strixtracker.repository.OwlSightingRepository
import org.springframework.stereotype.Service

@Service
class OwlSightingService(private val repository: OwlSightingRepository) {

    fun getSightingsForUser(username: String): List<OwlSighting> =
        repository.findAllByUserUsername(username)

    fun addSighting(sighting: OwlSighting) = repository.save(sighting)

    fun getSpeciesCountsForUser(username: String): Map<String, Long> {
        val rawCounts = repository.countSightingsBySpecies(username)
        return rawCounts.associate { it[0] as String to (it[1] as Long) }
    }
}