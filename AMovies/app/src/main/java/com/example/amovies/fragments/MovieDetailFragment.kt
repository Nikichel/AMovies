package com.example.amovies.fragments

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.amovies.R
import com.example.amovies.recycleViewMovies.MovieItem
import java.io.Serializable

class MovieDetailFragment(): Fragment() {

    private var listener: Listener? = null
    private var movieItem: MovieItem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        if(savedInstanceState!=null){
            val title = savedInstanceState.getString(TITLE).toString()
            val duration = savedInstanceState.getString(DURATION).toString()
            val movieTags = savedInstanceState.getString(MOVIE_TAGS).toString()
            val category = savedInstanceState.getString(CATEGORY).toString()
            val countReviews = savedInstanceState.getString(COUNT_REVIEWS).toString()
            val movieImageBackground = savedInstanceState.getInt(IMAGE_BACKGROUND)
            val storyline = savedInstanceState.getString(STORYLINE).toString()

            movieItem = MovieItem(title, duration,movieTags,countReviews,0, category,movieImageBackground, storyline)
        }
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(movieItem!=null) {
            loadLayout(view)
        }
        view.findViewById<TextView>(R.id.back_tv).setOnClickListener {
            Log.d("CheckStatusListener", "MovieDetailFragment::: ${listener.toString()}")
            listener?.goBack()
        }
    }

    private fun loadLayout(view: View){
        view.findViewById<ImageView>(R.id.img_bg).setImageResource(movieItem!!.movieImageBackgroundDetail)
        view.findViewById<TextView>(R.id.title).text = movieItem!!.title
        view.findViewById<TextView>(R.id.tag).text = movieItem!!.movieTags
        view.findViewById<TextView>(R.id.count_reviews).text = movieItem!!.countReviews
        view.findViewById<TextView>(R.id.category).text = movieItem!!.category
        view.findViewById<TextView>(R.id.storyline_text).text = movieItem!!.storyline
    }
    fun setMovieData(movieItem: MovieItem){
        this.movieItem = movieItem
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Listener)
            listener = context
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(movieItem!=null) {
            outState.apply {
                putString(TITLE, movieItem!!.title)
                putString(DURATION, movieItem!!.duration)
                putString(MOVIE_TAGS, movieItem!!.movieTags)
                putString(CATEGORY, movieItem!!.category)
                putString(COUNT_REVIEWS, movieItem!!.countReviews)
                putInt(IMAGE_BACKGROUND, movieItem!!.movieImageBackgroundDetail)
                putString(STORYLINE, movieItem!!.storyline)
            }
        }
    }

    companion object{
        interface Listener{
            fun goBack()
        }

        const val TITLE = "title"
        const val DURATION = "duration"
        const val MOVIE_TAGS = "movieTags"
        const val CATEGORY = "category"
        const val COUNT_REVIEWS = "countReviews"
        const val IMAGE_BACKGROUND = "imageBackgroundDetail"
        const val STORYLINE = "storyline"

    }
}