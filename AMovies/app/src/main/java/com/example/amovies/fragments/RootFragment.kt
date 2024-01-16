package com.example.amovies.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.amovies.R
import com.example.amovies.domain.MovieDataSource
import com.example.amovies.recycleView.RecycleMovieAdapter

class RootFragment : Fragment() {

    private var listener: Listener? = null
    private var recyclerView: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_root, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.list_movies)
        recyclerView?.adapter = RecycleMovieAdapter()
        recyclerView?.layoutManager = GridLayoutManager(requireActivity(), 2)
/*        requireActivity().supportFragmentManager.beginTransaction().add(R.id.preview_movie, MovieListFragment()).commit()

        view.findViewById<FrameLayout>(R.id.preview_movie).setOnClickListener {
            Log.d("CheckStatusListener", "RootFragment::: ${listener.toString()}")
            listener?.goDetail()
        }*/
    }

    override fun onStart() {
        super.onStart()
        (recyclerView?.adapter as? RecycleMovieAdapter)?.apply {
            bindMovies(MovieDataSource().getMovies())
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
        interface Listener{
            fun goDetail()
        }
    }
}