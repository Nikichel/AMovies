package com.example.amovies.movieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.amovies.data.MovieRepository
import com.example.amovies.moviesList.ViewModelMovies

class ViewModelMovieDetailFactory(private val repository: MovieRepository): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ViewModelMovieDetail(repository) as T
}
