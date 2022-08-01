package com.example.stubhub.repositories

import android.content.Context
import com.example.stubhub.models.Event
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class EventsRepository @Inject constructor(@ApplicationContext val context: Context){
    fun getEvents(name: String, price: String) = Json.decodeFromString<List<Event>>(
        context.assets.open("input.json").bufferedReader().use { it.readText() })
        .filter { name.isBlank() || it.artiste.contains(name, ignoreCase = true) }
        .filter { price.isBlank() || it.price <= price.toLong() }
}