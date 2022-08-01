package com.example.stubhub.repositories

import org.junit.Test

internal class EventsRepositoryTest {

    @Test
    fun `check events serialization`() {
        val repo = EventsRepository()
        assert(repo.getEvents().size == 10)
    }
}