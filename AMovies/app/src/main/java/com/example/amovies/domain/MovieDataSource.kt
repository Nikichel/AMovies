package com.example.amovies.domain

import com.example.amovies.R
import com.example.amovies.recycleViewMovies.MovieItem

class MovieDataSource {
    fun getMovies(): List<MovieItem> {
        return listOf(
            MovieItem(
                "Avengers: End Game",
                "182 MIN",
                "Action, Adventure, Fantasy",
                "125 Reviews",
                R.drawable.avengers_end_game,
                "13+",
                R.drawable.background_detail_avengers,
                "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."),
            MovieItem(
                "Tenet",
                "150 MIN",
                "Action, Sci-Fi, Thriller",
                "98 Reviews",
                R.drawable.tenet,
                "16+",
                R.drawable.background_detail_tenet,
                "In \"Tenet,\" a secret organization possesses the ability to manipulate time. The protagonist, known as the Protagonist, embarks on a dangerous mission to prevent a global catastrophe linked to time inversions. Throughout his journey, he encounters numerous dangers, intrigues, and puzzles, as the past, present, and future become indistinguishable."),
            MovieItem(
                "Black Widow",
                "133 MIN",
                "Action, Adventure, Sci-Fi",
                "38 Reviews",
                R.drawable.black_widow,
                "13+",
                R.drawable.background_detail_black_widow,
                "The film tells the story of Natasha Romanoff, also known as Black Widow, and her journey into the past. Natasha returns to Russia, where she confronts her former allies and family to unravel her dark past and deal with a secret organization known as the Red Room. During her adventure, she meets new allies and faces a powerful enemy."),
            MovieItem(
                "Wonder Woman 1984",
                "151 MIN",
                "Action, Adventure, Fantasy",
                "74 Reviews",
                R.drawable.wonder_woman_1984,
                "13+",
                R.drawable.background_detail_ww84,
                "The movie transports viewers to the 1980s, where Diana Prince, also known as Wonder Woman, works at the Smithsonian Museum. She encounters a mysterious artifact with the power to grant wishes. Soon, it becomes clear that using this artifact has dangerous consequences, and Diana must confront new challenges and enemies to save the world from a terrible threat.")
        )
    }
}