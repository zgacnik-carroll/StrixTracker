package com.strixtracker.repository

import com.strixtracker.model.OwlSighting
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OwlSightingRepository : JpaRepository<OwlSighting, Long> {

    fun findAllByUserUsername(username: String): List<OwlSighting>

    @Query("SELECT s.species, COUNT(s) FROM OwlSighting s WHERE s.user.username = :username GROUP BY s.species")
    fun countSightingsBySpecies(username: String): List<Array<Any>>
}