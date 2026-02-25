package com.strixtracker.repository

import com.strixtracker.model.OwlSighting
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OwlSightingRepository : JpaRepository<OwlSighting, Long>