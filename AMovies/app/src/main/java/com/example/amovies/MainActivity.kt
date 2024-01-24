package com.example.amovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.amovies.data.JsonMovieRepository
import com.example.amovies.data.MovieRepository
import com.example.amovies.movieDetail.MovieDetailFragment
import com.example.amovies.moviesList.RootFragment

class MainActivity : AppCompatActivity(),
    MovieDetailFragment.Companion.Listener,
    MovieRepositoryProvider {

    private val repoMovies = JsonMovieRepository(this)
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

    override fun provideMovieRepository(): MovieRepository = repoMovies

    /*    override fun goDetail() {
            supportFragmentManager.beginTransaction().addToBackStack(null).add(R.id.fragment_container, movieDetailFragment).commit()
        }*/
}