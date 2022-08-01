package com.example.stubhub.models

import kotlinx.serialization.Serializable

@Serializable
data class Event (
    val id: Long,
    val city: String,
    val artiste: String,
    val price: Double,
)