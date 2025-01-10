package com.example.movieapp

import com.example.movieapp.api.ApiHelper
import com.example.movieapp.api.ApiService

class Repository( private val apiHelper: ApiHelper,) {

     suspend fun getSearchMovies(searchQuery:String) = apiHelper.getSearchMovies(searchQuery)
}