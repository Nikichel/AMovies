package com.example.amovies

import com.example.amovies.data.MovieRepository

internal interface MovieRepositoryProvider {
    fun provideMovieRepository(): MovieRepository
}