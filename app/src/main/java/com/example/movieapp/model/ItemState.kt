package com.example.movieapp.model

data class ItemState(
    var item: GetSearchMovies? = null,
    var error: String = "",
    var isLoading: Boolean = false
)