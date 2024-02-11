package com.example.amovies.moviesList

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.amovies.data.MovieRepository
import com.example.amovies.model.Movie
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelMovies(private val repository: MovieRepository): ViewModel() {
    private val _moviesList = MutableStateFlow<List<Movie>>(emptyList())
    val moviesList: StateFlow<List<Movie>> get() = _moviesList

    init {
        loadMovies()
    }
    private fun loadMovies(){
        viewModelScope.launch {
            _moviesList.value = repository.loadMovies()
            Log.d("checkList", "${_moviesList.value}")
        }
    }
}