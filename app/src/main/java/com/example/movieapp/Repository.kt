package com.example.movieapp

import com.example.movieapp.api.ApiHelper
import com.example.movieapp.api.ApiService
import com.example.movieapp.model.GetSearchMovies
import com.example.movieapp.model.ResultState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class Repository( private val apiHelper: ApiHelper,) {

     fun getSearchMovies(searchQuery:String) = callbackFlow<GetSearchMovies> {
          apiHelper.getSearchMovies(searchQuery)

          awaitClose { close() }
     }
}