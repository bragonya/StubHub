package com.example.stubhub.repositories

import com.example.stubhub.models.Child
import com.example.stubhub.models.Event
import com.example.stubhub.utils.FileManager
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class EventsRepository @Inject constructor(val fileManager: FileManager){
    private val json = Json { ignoreUnknownKeys = true }

    fun getChildren(name: String, price: String) = json
        .decodeFromString<Child>(fileManager.readFromAsset("input.json"))
        .filter(name, price)
}