package com.e.nytimes.data.api

import com.e.nytimes.BuildConfig
import com.e.nytimes.data.model.NyTimesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("7.json")
    suspend fun getMostPopular(@Query("api-key") apiKey: String = BuildConfig.API_KEY): NyTimesModel
}