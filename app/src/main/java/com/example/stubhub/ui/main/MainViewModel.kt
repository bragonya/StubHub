package com.example.stubhub.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.stubhub.models.Event
import com.example.stubhub.repositories.EventsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: EventsRepository
): ViewModel()  {

    val events = MutableStateFlow<List<Event>>(emptyList())
    val name = mutableStateOf<String>("")
    val price = mutableStateOf<String>("")

    fun getEvents() {
        events.value = repository.getEvents(name.value, price.value)
    }
}