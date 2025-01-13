package com.example.movieapp.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SearchMoviesScreen(
    sharedViewModel: SharedViewModel = hiltViewModel()
){
   val movies = sharedViewModel.movies.collectAsState()


    if (movies.value.isLoading){
        Box(modifier = Modifier.fillMaxSize().background(color = Color.Gray)){
            Dialog(onDismissRequest = { }) {
                CircularProgressIndicator(color = Blue)
            }
        }
    }

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
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .padding(contentPadding)
                .background(color = Color.Gray),
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing = 8.dp
        ) {
            movies.value.item?.Search.let { it->
                itemsIndexed(it ?: emptyList()){ index,movies->
                    MovieCard(movies = movies,index, onItemClicked = {

                    })
                }

            }

        }
    }


}