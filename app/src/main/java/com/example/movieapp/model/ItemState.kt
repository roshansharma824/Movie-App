package com.example.movieapp.model

data class ItemState(
    var item: GetSearchMovies = GetSearchMovies( Response= "", Search = emptyList(), totalResults="" ),
    var error: String = "",
    var isLoading: Boolean = false
)