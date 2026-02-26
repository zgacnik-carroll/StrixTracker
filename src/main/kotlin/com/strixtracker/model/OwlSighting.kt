package com.strixtracker.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class OwlSighting(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    var species: String = "",
    var location: String = "",
    var dateTime: LocalDateTime = LocalDateTime.now(),
    var notes: String = "",

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null
)