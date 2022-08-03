package com.example.stubhub.models

import kotlinx.serialization.Serializable

@Serializable
data class Child (
    val id: Int,
    val name: String,
    val events: List<Event>,
    val children: List<Child>
) {

    fun filter(name: String, price: String): Child? {
        fun dfs(currentChild: Child): Child? {
            return if (currentChild.events.isNotEmpty()) {
                val filterEvents = currentChild.events.filter { name.isBlank() || it.city.contains(name, ignoreCase = true) }
                    .filter { price.isBlank() || it.price <= price.toLong() }
                if(filterEvents.isNotEmpty()) currentChild.copy(events = filterEvents) else null
            } else {
                val newChildren = mutableListOf<Child>()
                currentChild.children.forEach { subChild ->
                    dfs(subChild)?.let {
                        newChildren.add(it)
                    }
                }
                if(newChildren.isNotEmpty()) currentChild.copy(children = newChildren) else null
            }
        }
        return dfs(this)
    }
}