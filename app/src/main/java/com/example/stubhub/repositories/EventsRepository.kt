package com.example.stubhub.repositories

import com.example.stubhub.models.Event
import com.example.stubhub.utils.FileManager
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class EventsRepository @Inject constructor(val fileManager: FileManager){
    fun getEvents(name: String, price: String) = Json
        .decodeFromString<List<Event>>(fileManager.readFromAsset("input.json"))
        .filter { name.isBlank() || it.artiste.contains(name, ignoreCase = true) }
        .filter { price.isBlank() || it.price <= price.toLong() }
}