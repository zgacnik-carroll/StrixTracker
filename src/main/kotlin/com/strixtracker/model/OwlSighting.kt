package com.strixtracker.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class OwlSighting(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val species: String = "",
    val location: String = "",
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val notes: String = ""
)