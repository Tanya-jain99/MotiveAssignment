package com.tanya.motiveassignment.data.api.model

import com.google.gson.annotations.SerializedName

data class CityNameResponse(
    @SerializedName("adminCode1") val adminCode1: String,
    @SerializedName("lng") val lng: String,
    @SerializedName("geonameId") val geonameId: Int,
    @SerializedName("toponymName") val toponymName: String,
    @SerializedName("countryId") val countryId: String,
    @SerializedName("fcl") val fcl: String,
    @SerializedName("population") val population: Int,
    @SerializedName("countryCode") val countryCode: String,
    @SerializedName("name") val name: String,
    @SerializedName("fclName") val fclName: String,
    @SerializedName("countryName") val countryName: String,
    @SerializedName("fcodeName") val fcodeName: String,
    @SerializedName("adminName1") val adminName1: String,
    @SerializedName("lat") val lat: String,
    @SerializedName("fcode") val fcode: String
)
