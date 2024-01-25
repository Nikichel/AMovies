package com.example.amovies.moviesList

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amovies.MovieRepositoryProvider
import com.example.amovies.R
import com.example.amovies.movieDetail.MovieDetailFragment
import com.example.amovies.model.Movie
import kotlinx.coroutines.launch

class RootFragment : Fragment() {

    private val viewModel: ViewModelMovies by viewModels {
        ViewModelMoviesFactory((requireActivity() as MovieRepositoryProvider).provideMovieRepository())
    }

    private var listener: ListenerDetail? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is ListenerDetail)
            listener = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_root, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.list_movies).apply {
            this.layoutManager = GridLayoutManager(requireActivity(), 2)
            val adapter = RecycleMovieAdapter { movieData ->
                listener?.goDetail(movieData)
            }
            this.adapter = adapter

            loadMovies(adapter)
        }
    }

    private fun loadMovies(adapter: RecycleMovieAdapter){
        lifecycleScope.launch {
            viewModel.moviesList.collect{ listMovie ->
                    Log.d("CheckList", listMovie.toString())
                    adapter.submitList(listMovie)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object{
        interface ListenerDetail{
            fun goDetail(movie: Movie)
        }
    }
}