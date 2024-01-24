package com.example.amovies.moviesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.amovies.data.MovieRepository

class ViewModelMoviesFactory (private val repository: MovieRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ViewModelMovies(repository) as T
}