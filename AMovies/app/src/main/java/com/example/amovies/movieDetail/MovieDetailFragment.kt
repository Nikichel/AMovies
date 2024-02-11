package com.example.amovies.movieDetail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.amovies.MovieRepositoryProvider
import com.example.amovies.R
import com.example.amovies.model.Movie
import kotlinx.coroutines.launch

class MovieDetailFragment: Fragment() {

    private var listener: Listener? = null
    private var recyclerViewActors: RecyclerView? = null
    private val viewModel: ViewModelMovieDetail by viewModels {
        ViewModelMovieDetailFactory((requireActivity() as MovieRepositoryProvider).provideMovieRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = arguments?.getSerializable(PARAM_MOVIE_ID) as? Int ?: return
        Log.d("MovieInfoLog", "ID: $movieId")

        recyclerViewActors = view.findViewById(R.id.list_actors)
        recyclerViewActors.apply {
            this?.adapter = RecyclerActorsAdapter()
            this?.layoutManager =LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        }
        view.findViewById<TextView>(R.id.back_tv).setOnClickListener {
            listener?.goBack()
        }

        lifecycleScope.launch {
            viewModel.movie.collect{ movie ->
                movie?.let { bindUI(view, it) }
            }
        }

        viewModel.loadMovie(movieId)
    }

    private fun showMovieNotFoundError(){
        Toast.makeText(
            requireContext(),
            "Movie not found",
            Toast.LENGTH_LONG
        ).show()
    }
    private fun bindUI(view: View, movie: Movie) {
        Log.d("MovieInfoLog", movie.toString())
        updateMovieDetailsInfo(movie)
        val adapter = view.findViewById<RecyclerView>(R.id.list_actors).adapter as RecyclerActorsAdapter
        //adapter.submitList(movie.actors)
    }

    private fun updateMovieDetailsInfo(movie: Movie){
        view?.findViewById<TextView>(R.id.title)?.text = movie.title
        //view?.findViewById<TextView>(R.id.tag)?.text = movie.genres.joinToString(", ") { it.name }
        view?.findViewById<TextView>(R.id.count_reviews)?.text = "${movie.reviewCount} Reviews"
        view?.findViewById<TextView>(R.id.category)?.text = "${movie.pgAge}+"
        //view?.findViewById<TextView>(R.id.storyline_text)?.text = movie.storyLine
        //view?.findViewById<ImageView>(R.id.img_bg)?.load(movie.detailImageUrl)

        val stars = listOf<ImageView?>(
            view?.findViewById(R.id.first_star),
            view?.findViewById(R.id.second_star),
            view?.findViewById(R.id.third_star),
            view?.findViewById(R.id.fourth_star),
            view?.findViewById(R.id.first_star)
        )

        for(i in 0 until movie.rating){
            stars[i]?.setImageResource(R.drawable.fill_star)
        }
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

    companion object{

        fun create(movieId: Int) = MovieDetailFragment().also {
            val args = bundleOf(
                PARAM_MOVIE_ID to movieId
            )
            it.arguments = args
        }
        interface Listener{
            fun goBack()
        }
        private const val PARAM_MOVIE_ID = "movie_id"
    }
}