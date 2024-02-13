package com.example.amovies.remote

import com.example.amovies.remote.Responses.CastResponse
import com.example.amovies.remote.Responses.ConfigResponse
import com.example.amovies.remote.Responses.GenresResponse
import com.example.amovies.remote.Responses.MovieDetailsResponse
import com.example.amovies.remote.Responses.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {
    @GET("configuration")
    suspend fun loadConfiguration(): ConfigResponse

    @GET("movie/popular")
    suspend fun getMoviesList(): PopularMoviesResponse

    @GET("genre/movie/list")
    suspend fun loadGenres(): GenresResponse

    @GET("movie/{movie_id}")
    suspend fun loadMovieDetails(
        @Path("movie_id") movieId: Int
    ): MovieDetailsResponse

    @GET("movie/{movie_id}/credits")
    suspend fun loadMovieCredits(
        @Path("movie_id") movieId: Int
    ): CastResponse
}