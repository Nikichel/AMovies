package com.example.amovies.model

data class Movie(
    val id: Int,
    val pgAge: Int,
    val title: String,
    val genres: List<Genre>,
    val runningTime: Int,
    val reviewCount: Int,
    var isLiked: Boolean,
    val rating: Int,
    val imageUrl: String?,
)