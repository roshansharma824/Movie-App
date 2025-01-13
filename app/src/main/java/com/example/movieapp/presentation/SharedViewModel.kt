package com.example.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.Repository
import com.example.movieapp.api.ApiHelper
import com.example.movieapp.api.RetrofitBuilder
import com.example.movieapp.model.ItemState
import com.example.movieapp.model.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor():ViewModel(){
    val repository = Repository(
        ApiHelper(RetrofitBuilder.apiService)
    )

    private val _movies = MutableStateFlow(ItemState())
    val movies = _movies.asStateFlow()

    //second state the text typed by the user
    val _searchText = MutableStateFlow("Iron")
    val searchText = _searchText.asStateFlow()

    init {
        initialize()
    }

    @OptIn(FlowPreview::class)
    private fun initialize() {
        viewModelScope.launch {
            _searchText
                .debounce(1000) // Debounce for 500ms
                .collectLatest { input ->
                    _movies.value = ItemState(isLoading = true) // Create a new instance to emit
                    try {
                        // Fetch movies from repository
                        val result = repository.getSearchMovies(input)
                        _movies.value = ItemState(item = result) // Create new instance with movies
                    } catch (e: Exception) {
                        _movies.value = ItemState(error = e.message.toString()) // Emit new error state
                    }
                }
        }
    }
}