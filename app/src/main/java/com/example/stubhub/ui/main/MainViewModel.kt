package com.example.stubhub.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.stubhub.models.Child
import com.example.stubhub.repositories.EventsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: EventsRepository
): ViewModel()  {

    val events = MutableStateFlow<MainState>(MainState.Loading)
    val name = mutableStateOf("")
    val price = mutableStateOf("")

    fun getRootChild() {
        events.value = MainState.Success(repository.getChildren(name.value, price.value))
    }
}

sealed class MainState {
    data class Success(val rootChild: Child?): MainState()
    object Loading: MainState()
}