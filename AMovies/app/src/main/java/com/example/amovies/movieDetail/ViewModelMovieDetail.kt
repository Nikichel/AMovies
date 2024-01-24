package com.example.amovies.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amovies.data.MovieRepository
import com.example.amovies.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelMovieDetail(private val repository: MovieRepository): ViewModel() {

    private val _movie = MutableStateFlow<Movie?>(null)

    val movie: StateFlow<Movie?> get() = _movie

    fun loadMovie(movieId: Int){
        viewModelScope.launch {
            _movie.value = repository.loadMovie(movieId)
        }

    }
}