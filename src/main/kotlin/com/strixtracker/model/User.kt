package com.strixtracker.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true, nullable = false)
    val username: String,

    @Column(nullable = false, length = 100)
    val password: String?,

    val role: String = "USER",

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val sightings: List<OwlSighting> = emptyList()
) {
    // No-arg constructor for Hibernate / Spring Security
    constructor() : this(0, "", "", "USER", emptyList())
}