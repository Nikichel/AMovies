package com.example.amovies.movieDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amovies.data.MovieRepository
import com.example.amovies.model.Movie
import com.example.amovies.model.MovieDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelMovieDetail(private val repository: MovieRepository): ViewModel() {

    private val _movie =  MutableLiveData<MovieDetails>()

    val movie: LiveData<MovieDetails> get() = _movie

    fun loadMovie(movieId: Int){
        viewModelScope.launch {
            try{
                _movie.value = repository.loadMovie(movieId)
            }catch (e: Exception){
                Log.d("ViewModelMovieDetail", e.toString())
            }
        }

    }
}