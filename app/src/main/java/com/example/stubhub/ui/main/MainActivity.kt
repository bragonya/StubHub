package com.example.stubhub.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.stubhub.ui.theme.StubHubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StubHubTheme {
                val events by viewModel.events.collectAsState()
                val name by viewModel.name
                val price by viewModel.price
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EventsScreen(
                        listOfEvents = events,
                        valueName = name,
                        onValueChangeName = { viewModel.name.value = it },
                        valuePrice = price,
                        onValueChangePrice = { viewModel.price.value = it },
                        onSearchButton = { viewModel.getEvents() }
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getEvents()
    }
}

