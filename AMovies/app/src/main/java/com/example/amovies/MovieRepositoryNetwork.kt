package com.example.amovies

import com.example.amovies.data.MovieRepository
import com.example.amovies.model.Movie
import com.example.amovies.remote.RetrofitDataSource

class MovieRepositoryNetwork(private val remoteDataSource: RetrofitDataSource): MovieRepository{
    override suspend fun loadMovies(): List<Movie> {
        return remoteDataSource.loadMovies()
    }

    override suspend fun loadMovie(movieId: Int): Movie? {
        TODO("Not yet implemented")
    }

}
