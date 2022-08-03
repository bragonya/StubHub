package com.example.stubhub.repositories

import com.example.stubhub.utils.FileManager
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import java.io.File

internal class EventsRepositoryTest {

    @Test
    fun `check events serialization without search terms`() {
        val fileManager:FileManager = mockk()
        every { fileManager.readFromAsset(any()) } returns File("./src/assets/input.json").readText()
        val repo = EventsRepository(fileManager)
        val children = repo.getChildren("", "")
        assert(children.children.size == 3)
        assert(children.children[0].children.size == 3)
        assert(children.children[0].children[0].children.size == 0)
        assert(children.children[0].children[0].events.size == 3)
    }
}