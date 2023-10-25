package com.tanya.motiveassignment.data.api

import com.tanya.motiveassignment.data.api.model.CitySearchResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("/searchJSON")
    suspend fun search(@QueryMap options : Map<String, String>): CitySearchResponse
}