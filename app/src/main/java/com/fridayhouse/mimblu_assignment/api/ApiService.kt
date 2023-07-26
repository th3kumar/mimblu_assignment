package com.fridayhouse.mimblu_assignment.api

import com.fridayhouse.mimblu_assignment.data.ApiResponse
import com.fridayhouse.mimblu_assignment.data.MatchOptionsApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {

    @GET("symptoms")
    suspend fun getSymptoms(): ApiResponse

    @GET("all")
    suspend fun getMatchOptions(): MatchOptionsApiResponse
}

