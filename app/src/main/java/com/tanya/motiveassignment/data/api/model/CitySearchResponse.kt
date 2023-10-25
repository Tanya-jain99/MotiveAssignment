package com.tanya.motiveassignment.data.api.model

import com.google.gson.annotations.SerializedName

data class CitySearchResponse(
@SerializedName("totalResultsCount")
val totalResultsCount: Int = 0,
@SerializedName("geonames")
val cities: List<CityNameResponse> ,
)
