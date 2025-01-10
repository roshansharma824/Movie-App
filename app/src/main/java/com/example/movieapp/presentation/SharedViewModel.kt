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
    val _searchText = MutableStateFlow("spider")
    val searchText = _searchText.asStateFlow()

    init {
        initialize()
//        getSearchMovies()
    }

    private fun getSearchMovies(){
        _movies.value.isLoading = true
        try {
            viewModelScope.launch(Dispatchers.IO){
                repository.getSearchMovies(_searchText.value).collect{ result->
                    _movies.value.item = result
                }

            }
        }catch (e:Exception){
            _movies.value.error = e.message.toString()
        }

    }

    @OptIn(FlowPreview::class)
    fun initialize() {
        viewModelScope.launch {
            _searchText.debounce(timeoutMillis = 500).collectLatest { input ->
                _movies.value.isLoading = true
                try {
                    viewModelScope.launch(Dispatchers.IO){
                        repository.getSearchMovies(_searchText.value).collect{ result->
                            _movies.value.item = result
                        }

                    }
                }catch (e:Exception){
                    _movies.value.error = e.message.toString()
                }
            }
        }
    }
}