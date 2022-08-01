package com.example.stubhub.repositories

import android.content.Context
import com.example.stubhub.utils.FileManager
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import java.io.BufferedReader
import java.io.File

internal class EventsRepositoryTest {

    @Test
    fun `check events serialization without search terms`() {
        val fileManager:FileManager = mockk()
        every { fileManager.readFromAsset(any()) } returns File("./src/assets/input.json").readText()
        val repo = EventsRepository(fileManager)
        assert(repo.getEvents("", "").size == 10)
    }
}