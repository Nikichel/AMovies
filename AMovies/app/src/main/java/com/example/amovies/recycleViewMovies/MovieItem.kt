package com.example.amovies.recycleViewMovies

import com.example.amovies.recycleViewActor.ActorItem

data class MovieItem(
    val title: String,
    val duration: String,
    val movieTags: String,
    val countReviews: String,
    val movieImageBackground: Int,
    val category: String,
    val movieImageBackgroundDetail: Int,
    val storyline: String
)
