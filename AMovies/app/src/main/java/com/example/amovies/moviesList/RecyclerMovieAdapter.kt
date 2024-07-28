package com.example.amovies.moviesList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.amovies.R
import com.example.amovies.model.Movie

class RecycleMovieAdapter(private val onClickCard: (item: Movie) -> Unit):
    ListAdapter<Movie, RecycleMovieAdapter.MovieViewHolder>(DiffCallback()){
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val title: TextView = itemView.findViewById(R.id.title)
        private val duration: TextView = itemView.findViewById(R.id.duration)
        private val movieTags: TextView = itemView.findViewById(R.id.tag_list_movie)
        private val countReviews: TextView = itemView.findViewById(R.id.count_reviews)
        private val movieImageBackground: ImageView = itemView.findViewById(R.id.movie_bg)
        private val category: TextView = itemView.findViewById(R.id.category)
        private val liked: View = itemView.findViewById(R.id.liked)
        private val stars: List<ImageView> = listOf(
            itemView.findViewById(R.id.first_star),
            itemView.findViewById(R.id.second_star),
            itemView.findViewById(R.id.third_star),
            itemView.findViewById(R.id.fourth_star),
            itemView.findViewById(R.id.fifth_star)
        )

        fun bind(movie: Movie, onClickCard:  (item: Movie) -> Unit){
            title.text = movie.title
            duration.text = "${movie.runningTime} MIN"
            movieTags.text = movie.genres.joinToString(", "){ it.name }

            category.text = "${movie.pgAge}+"
            countReviews.text = "${movie.reviewCount} Reviews"

            isLiked(movie)

            for(i in 0 until movie.rating/2){
                stars[i].setImageResource(R.drawable.fill_star)
            }

            Log.d("TMDB_IMG_LOAD", movie.imageUrl.toString())
            movieImageBackground.load(movie.imageUrl){
                placeholder(R.drawable.download_img)
                error(R.drawable.download_error)
            }
            liked.setOnClickListener {
                movie.isLiked = !movie.isLiked
                isLiked(movie)
            }
            itemView.setOnClickListener {
                onClickCard(movie)
            }
        }

        private fun isLiked(movie: Movie) {
            if (movie.isLiked)
                liked.setBackgroundResource(R.drawable.liked)
            else
                liked.setBackgroundResource(R.drawable.unliked)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater.inflate(R.layout.item_movie_list, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClickCard)
    }
}