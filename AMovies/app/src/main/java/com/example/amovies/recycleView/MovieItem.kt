package com.example.amovies.recycleView

import android.widget.ImageView
import android.widget.TextView
import com.example.amovies.R

data class MovieItem(
    val title: String,
    val duration: String,
    val movieTags: String,
    val countReviews: String,
    val movieImageBackground: Int,
    val category: String
)
