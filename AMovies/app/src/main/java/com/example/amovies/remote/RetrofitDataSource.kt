package com.example.amovies.remote

import com.example.amovies.model.Movie

class RetrofitDataSource (
    private val api: MovieApiService,
    private val imageUrlAppender: ImageUrlCreator,
) : RemoteDataSource {
    override suspend fun loadMovies(): List<Movie> {
        TODO("Not yet implemented")
    }
}