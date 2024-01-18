package com.example.amovies.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amovies.R
import com.example.amovies.data.JsonMovieRepository
import com.example.amovies.model.Movie
import com.example.amovies.recycleViewMovies.ListenerClick
import com.example.amovies.recycleViewMovies.RecycleMovieAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class RootFragment : Fragment() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var recyclerView: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_root, container, false)
    }

    private val clickListener = object: ListenerClick {
        override fun onClick(movieItem: Movie) {
            val fragmentDetail = MovieDetailFragment()
            fragmentDetail.setMovieData(movieItem)
            requireActivity().supportFragmentManager.beginTransaction().addToBackStack(null).add(R.id.fragment_container, fragmentDetail).commit()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.list_movies)
        recyclerView?.adapter = RecycleMovieAdapter(clickListener)
        recyclerView?.layoutManager = GridLayoutManager(requireActivity(), 2)
    }

    override fun onStart() {
        super.onStart()
        scope.launch{
            val movies = JsonMovieRepository(requireActivity()).loadMovies()
            (recyclerView?.adapter as? RecycleMovieAdapter)?.apply {
                bindMovies(movies)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        recyclerView = null
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

/*    companion object{
        interface Listener{
            fun goDetail()
        }
    }*/
}