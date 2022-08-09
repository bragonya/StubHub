package com.example.stubhub.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stubhub.ui.theme.StubHubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StubHubTheme {
                val state by viewModel.events.collectAsState()
                val name by viewModel.name
                val price by viewModel.price
                val cheapestChecked by viewModel.cheapest
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when(val rState = state) {
                        is MainState.Success -> EventsScreen(
                                                    rootChild = rState.rootChild,
                                                    valueName = name,
                                                    onValueChangeName = { viewModel.name.value = it },
                                                    valuePrice = price,
                                                    onValueChangePrice = { viewModel.price.value = it },
                                                    onSearchButton = { viewModel.getRootChild() },
                                                    cheapestChecked = cheapestChecked,
                                                    onCheapestChanged = { viewModel.cheapest.value = it }
                                                )
                        is MainState.Loading -> Text(text = "Loading")
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRootChild()
    }
}

