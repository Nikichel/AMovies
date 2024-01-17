package com.example.amovies.recycleViewMovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.amovies.R
import com.example.amovies.model.Movie

class RecycleMovieAdapter(private val listener: ListenerClick): RecyclerView.Adapter<RecycleMovieAdapter.MovieViewHolder>() {

    private var movies = listOf<Movie>()
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val title: TextView = itemView.findViewById(R.id.title)
        private val duration: TextView = itemView.findViewById(R.id.duration)
        private val movieTags: TextView = itemView.findViewById(R.id.tag_list_movie)
        private val countReviews: TextView = itemView.findViewById(R.id.count_reviews)
        private val movieImageBackground: ImageView = itemView.findViewById(R.id.movie_bg)
        private val category: TextView = itemView.findViewById(R.id.category)
        private val liked: View = itemView.findViewById(R.id.liked)
        private val starts: List<ImageView> = listOf(
            itemView.findViewById(R.id.first_star),
            itemView.findViewById(R.id.second_star),
            itemView.findViewById(R.id.third_star),
            itemView.findViewById(R.id.fourth_star),
            itemView.findViewById(R.id.fifth_star)
        )

        fun bind(movie: Movie){
            title.text = movie.title
            duration.text = "${movie.runningTime} MIN"
            movieTags.text = movie.genres.joinToString(", "){ it.name }

            //movieImageBackground.setImageResource(movieItem.movieImageBackground)

            category.text = "${movie.pgAge}+"
            countReviews.text = "${movie.reviewCount.toString()} Reviews"

            if(movie.isLiked)
                liked.setBackgroundResource(R.drawable.liked)
            else
                liked.setBackgroundResource(R.drawable.unliked)

            for(i in 0 until movie.rating){
                starts[i].setImageResource(R.drawable.fill_star)
            }
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
        holder.itemView.setOnClickListener{
            listener.onClick(movies[position])
        }

        holder.itemView.findViewById<View>(R.id.liked).setOnClickListener{
            movies[position].isLiked = !movies[position].isLiked
            notifyItemChanged(position)
        }
    }

    fun bindMovies(listMovies: List<Movie>){
        movies = listMovies
        notifyDataSetChanged()

    }
}

interface ListenerClick{
    fun onClick(movieItem: Movie)
}