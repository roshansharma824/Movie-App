package com.example.movieapp.model

data class GetSearchMovies(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)