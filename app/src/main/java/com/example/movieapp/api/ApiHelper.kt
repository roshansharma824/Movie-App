package com.example.movieapp.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getSearchMovies(searchQuery:String) = apiService.getSearchMovies(searchQuery)
}