package com.strixtracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StrixTrackerApplication

fun main(args: Array<String>) {
    runApplication<StrixTrackerApplication>(*args)
}
