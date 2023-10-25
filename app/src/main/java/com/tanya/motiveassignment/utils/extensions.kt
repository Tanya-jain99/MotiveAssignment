package com.tanya.motiveassignment.utils

import androidx.appcompat.widget.SearchView
import com.tanya.motiveassignment.data.api.model.CityNameResponse
import com.tanya.motiveassignment.data.model.City
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

 fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {

    val query = MutableStateFlow("")

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            query.value = newText
            return true
        }
    })

    return query

}

fun CityNameResponse.asModel(city: String, state: String, country:String) =
    City(
        city = city,
        state = state,
        country = country,
    )

