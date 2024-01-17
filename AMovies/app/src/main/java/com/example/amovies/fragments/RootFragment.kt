package com.example.amovies.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amovies.R
import com.example.amovies.domain.MovieDataSource
import com.example.amovies.recycleViewMovies.ListenerClick
import com.example.amovies.recycleViewMovies.MovieItem
import com.example.amovies.recycleViewMovies.RecycleMovieAdapter

class RootFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_root, container, false)
    }

    private val clickListener = object: ListenerClick {
        override fun onClick(movieItem: MovieItem) {
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
        (recyclerView?.adapter as? RecycleMovieAdapter)?.apply {
            bindMovies(MovieDataSource().getMovies())
        }
    }

    override fun onDetach() {
        super.onDetach()
        recyclerView = null
    }

/*    companion object{
        interface Listener{
            fun goDetail()
        }
    }*/
}