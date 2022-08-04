package com.example.stubhub.models

import kotlinx.serialization.Serializable

@Serializable
data class Event (
    val id: Long,
    val city: String,
    val name: String,
    val price: Double,
    val venueName: String,
    val distanceFromVenue: Double,
    val date: String
)