package com.example.amovies.remote

import com.example.amovies.model.Movie
import com.example.amovies.model.MovieDetails

interface RemoteDataSource {

    suspend fun loadMovies(): List<Movie>
    suspend fun loadMovie(movieId: Int): MovieDetails
}