package com.example.amovies.remote

import com.example.amovies.remote.Responses.ConfigResponse
import com.example.amovies.remote.Responses.PopularMoviesResponse
import retrofit2.http.GET

interface MovieApiService {
    @GET("configuration")
    suspend fun loadConfiguration(): ConfigResponse

    @GET("movie/popular")
    suspend fun getMoviesList(): PopularMoviesResponse
}