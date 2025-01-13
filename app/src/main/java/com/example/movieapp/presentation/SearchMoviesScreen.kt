package com.example.movieapp.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SearchMoviesScreen(
    sharedViewModel: SharedViewModel = hiltViewModel()
){
   val movies = sharedViewModel.movies.collectAsState()


//    if (movies.value.isLoading){
//        Box(modifier = Modifier.fillMaxSize()){
////            CircularProgressIndicator()
//        }
//    }

    Scaffold(
        topBar = {
            SearchScreenTopBar(
                onQueryChanged = {
                    sharedViewModel._searchText.value = it
                },
                onClearQuery = {

                }
            )
        }
    ) { contentPadding->
        LazyColumn(
            Modifier
                .padding(contentPadding)
                .background(color = Color.Gray)) {
            movies.value.item?.Search.let { it->
                items(it ?: emptyList()){ movies->
                    MovieCard(movies = movies)
                }

            }

        }
    }


}