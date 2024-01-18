package com.example.amovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.amovies.data.JsonMovieRepository
import com.example.amovies.fragments.MovieDetailFragment
import com.example.amovies.fragments.RootFragment
import com.example.amovies.model.Actor
import com.example.amovies.model.Movie
import com.example.amovies.recycleViewMovies.RecycleMovieAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), MovieDetailFragment.Companion.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, RootFragment())
                .commit()
    }

    override fun goBack() {
        val lastFragment = supportFragmentManager.fragments.last()
        supportFragmentManager.beginTransaction()
            .apply {
                remove(lastFragment)
                commit()
            }
    }

/*    override fun goDetail() {
        supportFragmentManager.beginTransaction().addToBackStack(null).add(R.id.fragment_container, movieDetailFragment).commit()
    }*/
}