package com.example.amovies.remote

import com.example.amovies.model.Movie

interface RemoteDataSource {

    suspend fun loadMovies(): List<Movie>
    /*suspend fun loadMovie(movieId: Int)*/
}