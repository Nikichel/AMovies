package com.example.amovies.moviesList

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
/*    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())*/
    //private var recyclerView: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_root, container, false)
    }

    private val clickListener = object: ListenerClick {
        override fun onClick(movieItem: Movie) {
            val fragmentDetail = MovieDetailFragment()
            fragmentDetail.addMovie(movieItem)
            requireActivity().supportFragmentManager.beginTransaction().addToBackStack(null).add(R.id.fragment_container, fragmentDetail).commit()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.list_movies).apply {
            this.layoutManager = GridLayoutManager(requireActivity(), 2)
            val adapter = RecycleMovieAdapter(clickListener)
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

    override fun onStart() {
        super.onStart()
/*        scope.launch{
            val movies = JsonMovieRepository(requireActivity()).loadMovies()
            (recyclerView?.adapter as? RecycleMovieAdapter)?.apply {
                bindMovies(movies)
            }
        }*/
    }

    override fun onDetach() {
        super.onDetach()
        //recyclerView = null
    }

    override fun onDestroy() {
        super.onDestroy()
        //scope.cancel()
    }

/*    companion object{
        interface Listener{
            fun goDetail()
        }
    }*/
}