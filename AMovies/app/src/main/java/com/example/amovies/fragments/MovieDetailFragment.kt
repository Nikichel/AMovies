package com.example.amovies.fragments

import android.content.Context
import android.content.pm.ModuleInfo
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amovies.R
import com.example.amovies.model.Movie
import com.example.amovies.recycleViewActor.RecyclerActorsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailFragment: Fragment() {

    private var movie: Movie? = null
    private var listener: Listener? = null
    private var recyclerViewActors: RecyclerView? = null
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            movie = savedInstanceState.getSerializable(MOVIE) as Movie
        }
        scope.launch { loadLayout(view) }
        recyclerViewActors = view.findViewById(R.id.list_actors)
        recyclerViewActors?.adapter = RecyclerActorsAdapter()
        recyclerViewActors?.layoutManager =LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        view.findViewById<TextView>(R.id.back_tv).setOnClickListener {
            listener?.goBack()
        }
    }

    override fun onStart() {
        super.onStart()
        (recyclerViewActors?.adapter as? RecyclerActorsAdapter)?.apply {
            if(movie!=null)
                bindActors(movie!!.actors)
        }
    }

    fun addMovie(movie: Movie){
        this.movie = movie
    }

    private suspend fun loadLayout(view: View) = withContext(Dispatchers.Main){
        if(movie!=null) {
            view.findViewById<TextView>(R.id.title).text = movie!!.title
            view.findViewById<TextView>(R.id.tag).text = movie!!.genres.joinToString(", ") { it.name }
            view.findViewById<TextView>(R.id.count_reviews).text = "${movie!!.reviewCount} Reviews"
            view.findViewById<TextView>(R.id.category).text = "${movie!!.pgAge}+"
            view.findViewById<TextView>(R.id.storyline_text).text = movie!!.storyLine
            view.findViewById<ImageView>(R.id.img_bg).setImageBitmap(loadImage(movie!!.detailImageUrl))
        }
    }

    private suspend fun loadImage(url: String): Bitmap {
        return withContext(Dispatchers.IO) {
            Glide.with(requireActivity())
                .asBitmap()
                .load(url)
                .submit()
                .get()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
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
        outState.putSerializable(MOVIE, movie)
    }

    companion object{
        interface Listener{
            fun goBack()
        }

        const val MOVIE = "movie"

    }
}