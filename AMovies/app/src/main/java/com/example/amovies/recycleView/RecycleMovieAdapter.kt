package com.example.amovies.recycleView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import com.example.amovies.R

class RecycleMovieAdapter: RecyclerView.Adapter<RecycleMovieAdapter.MovieViewHolder>() {

    private var movies = listOf<MovieItem>()
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val title: TextView = itemView.findViewById(R.id.title)
        private val duration: TextView = itemView.findViewById(R.id.duration)
        private val movieTags: TextView = itemView.findViewById(R.id.tag_list_movie)
        private val countReviwes: TextView = itemView.findViewById(R.id.count_reviews)
        private val movieImageBackground: ImageView = itemView.findViewById(R.id.movie_bg)
        private val category: TextView = itemView.findViewById(R.id.category)

        fun bind(movieItem: MovieItem){
            title.text = movieItem.title
            duration.text = movieItem.duration
            movieTags.text = movieItem.movieTags
            movieImageBackground.setImageResource(movieItem.movieImageBackground)
            category.text = movieItem.category
            countReviwes.text = movieItem.countReviews

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater.inflate(R.layout.item_movie_list, parent, false))

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun bindMovies(listMovies: List<MovieItem>){
        movies = listMovies
        notifyDataSetChanged()

    }
}