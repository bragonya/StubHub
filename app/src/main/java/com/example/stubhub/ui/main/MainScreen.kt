package com.example.stubhub.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.stubhub.models.Event
import com.example.stubhub.ui.theme.StubHubTheme

@Composable
fun EventsScreen(
    listOfEvents: List<Event>,
    valueName: String,
    onValueChangeName: (String) -> Unit,
    valuePrice: String,
    onValueChangePrice: (String) -> Unit,
    onSearchButton: () -> Unit,
) {
    Column {
        TopPanel(valueName, onValueChangeName, valuePrice, onValueChangePrice, onSearchButton)
        ContentList(listOfEvents)
    }
}

@Composable
private fun ContentList(listOfEvents: List<Event>) {
    LazyColumn {
        items(listOfEvents) { event ->
            Text(text = event.artiste)
        }
    }
}

@Composable
private fun TopPanel(
    valueName: String,
    onValueChangeName: (String) -> Unit,
    valuePrice: String,
    onValueChangePrice: (String) -> Unit,
    onSearchButton: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Name:")
            BasicTextField(
                value = valueName,
                onValueChange = onValueChangeName
            )

            Text(text = "Price Less Than:")
            BasicTextField(
                value = valuePrice,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = onValueChangePrice
            )
        }

        Button(onClick = onSearchButton) {
            Text(text = "Search")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventScreenPreview() {
    StubHubTheme {
        ContentList(
            listOf(
                Event(
                    id = 1929313L,
                    artiste = "Freddy Mercury",
                    city = "Vancouver",
                    price = 13.9
                ),
                Event(
                    id = 1929314L,
                    artiste = "John Lennon",
                    city = "Houston",
                    price = 23.9
                ),
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopPanelPreview() {
    StubHubTheme {
        TopPanel(
            "",
            {},
            "",
            {},
            {}
        )
    }
}