package com.example.movieapp.api

import com.example.movieapp.model.GetSearchMovies
import com.example.movieapp.model.ResultState
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/")
    suspend fun getSearchMovies(
        @Query("s") s: String,
        @Query("apikey") apikey: String = "a90322be",
        @Query("page") page: Int = 1,
    ): GetSearchMovies
}