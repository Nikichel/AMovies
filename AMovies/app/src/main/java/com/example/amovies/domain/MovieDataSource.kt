package com.example.amovies.domain

import com.example.amovies.R
import com.example.amovies.recycleView.MovieItem

class MovieDataSource {
    fun getMovies(): List<MovieItem> {
        return listOf(
            MovieItem(
                "Avengers: End Game",
                "137 min",
                "Action, Adventure, Fantasy",
                "125 Reviews",
                R.drawable.avengers_end_game,
                "13+"),
            MovieItem(
                "Tenet",
                "97 min",
                "Action, Sci-Fi, Thriller",
                "98 Reviews",
                R.drawable.tanet,
                "16+"),
            MovieItem(
                "Black Widow",
                "102 min",
                "Action, Adventure, Sci-Fi",
                "38 Reviews",
                R.drawable.black_widow,
                "13+"),
            MovieItem(
                "Wonder Woman 1984",
                "120 min",
                "Action, Adventure, Fantasy",
                "74 Reviews",
                R.drawable.wonder_woman_1984,
                "13+")
        )
    }
}