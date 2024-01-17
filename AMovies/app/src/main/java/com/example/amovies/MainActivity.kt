package com.example.amovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.amovies.data.JsonMovieRepository
import com.example.amovies.fragments.MovieDetailFragment
import com.example.amovies.fragments.RootFragment
import com.example.amovies.model.Actor
import com.example.amovies.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), MovieDetailFragment.Companion.Listener {

    private val rootFragment = RootFragment()
    private val scope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, rootFragment)
                .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
    override fun goBack() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, rootFragment).commit()
    }

/*    override fun goDetail() {
        supportFragmentManager.beginTransaction().addToBackStack(null).add(R.id.fragment_container, movieDetailFragment).commit()
    }*/
}