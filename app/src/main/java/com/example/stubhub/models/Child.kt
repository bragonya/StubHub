package com.example.stubhub.models

import kotlinx.serialization.Serializable

@Serializable
data class Child (
    val id: Int,
    val name: String,
    val events: List<Event>,
    val children: List<Child>,
) {

    fun filter(name: String, price: String, cheapest: Boolean): Child? {
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

        fun cheapest(currentChild: Child?): Pair<Double, Child?> {
            if(currentChild == null) return 0.0 to null
            return if(currentChild.events.isNotEmpty()) {
                val cheapestEvent = currentChild.events.minBy { it.price }
                cheapestEvent.price to currentChild.copy(events = listOf(cheapestEvent))
            } else {
                var min = Double.MAX_VALUE
                var minNode: Child? = null
                currentChild.children.forEach { subChild ->
                    cheapest(subChild).let { (price, node) ->
                        if(price <= min){
                            minNode = node
                            min = price
                        }
                    }
                }
                min to currentChild.copy(children = listOf(minNode!!))
            }
        }

        val filteredList = dfs(this)
        return  if(cheapest) cheapest(filteredList).second else filteredList
    }
}