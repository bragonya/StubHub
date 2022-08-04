package com.example.stubhub.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stubhub.models.Child
import com.example.stubhub.models.Event
import com.example.stubhub.ui.theme.StubHubTheme
import kotlin.math.max
import kotlin.math.min

@Composable
fun EventsScreen(
    rootChild: Child?,
    valueName: String,
    onValueChangeName: (String) -> Unit,
    valuePrice: String,
    onValueChangePrice: (String) -> Unit,
    onSearchButton: () -> Unit,
) {
    Column {
        TopPanel(valueName, onValueChangeName, valuePrice, onValueChangePrice, onSearchButton)

        rootChild?.let {
            Text(
                modifier = Modifier.padding(8.dp),
                text = rootChild.name,
                style = MaterialTheme.typography.headlineLarge
            )
            CategoryList(rootChild)
        } ?: Text(
            text = "No results found",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun CategoryList(rootChild: Child) {
    LazyColumn() {
        items(rootChild.children) { child ->
            Column {
                val stack = mutableListOf<Pair<Child, Int>>()
                stack.add(child to 0)
                while (stack.isNotEmpty()) {
                    val (currentChild, level) = stack.removeAt(0)
                    val textSize = (max(25 - level * 5, 5)).sp
                    if(currentChild.children.isNotEmpty()) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = currentChild.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = textSize
                        )
                        stack.addAll(0, currentChild.children.map { it to level + 1 })
                    } else {
                        EventList(currentChild, textSize)
                    }
                }
            }
        }
    }
}

@Composable
private fun EventList(child: Child, textSize: TextUnit) {
    Column {
        Text(
            modifier = Modifier.padding(8.dp),
            text = child.name,
            style = MaterialTheme.typography.titleSmall,
            fontSize = textSize
        )
        child.events.forEach { event ->
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                Row() {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.8f)
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = "#${event.id}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                        )
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            text = event.city,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            text = event.date,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            text = event.venueName,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            text = event.distanceFromVenue.toString(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "$${event.price}",
                        style = MaterialTheme.typography.labelLarge
                    )
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopPanel(
    valueName: String,
    onValueChangeName: (String) -> Unit,
    valuePrice: String,
    onValueChangePrice: (String) -> Unit,
    onSearchButton: () -> Unit
) {
    ElevatedCard {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(8.dp),
                    value = valueName,
                    onValueChange = onValueChangeName,
                    label = { Text(text = "Name:") }
                )

                OutlinedTextField(
                    modifier = Modifier.padding(8.dp),
                    value = valuePrice,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = onValueChangePrice,
                    label = { Text(text = "Price Less Than:") }
                )
            }

            Button(
                onClick = onSearchButton,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(text = "Search")
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun EventScreenPreview() {
    StubHubTheme {
        EventList(
            Child(
                id = 123,
                name = "Hip Hop",
                children = emptyList(),
                events = listOf(
                    Event(
                        id = 1929313L,
                        name = "Freddy Mercury",
                        city = "Vancouver",
                        price = 13.9,
                        venueName = "3rd avenue",
                        date = "3 July 2022",
                        distanceFromVenue = 312.1321
                    ),
                    Event(
                        id = 1929314L,
                        name = "John Lennon",
                        city = "Houston",
                        price = 23.9,
                        venueName = "3rd avenue",
                        date = "3 July 2022",
                        distanceFromVenue = 312.1321
                    ),
                )
            ),
            25.sp
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