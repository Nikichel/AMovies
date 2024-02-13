package com.example.amovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.amovies.data.MovieRepository
import com.example.amovies.model.Movie
import com.example.amovies.movieDetail.MovieDetailFragment
import com.example.amovies.moviesList.RootFragment
import com.example.amovies.remote.ImageUrlCreator
import com.example.amovies.remote.NetworkModule
import com.example.amovies.remote.RetrofitDataSource

class MainActivity : AppCompatActivity(),
    RootFragment.Companion.ListenerDetail,
    MovieDetailFragment.Companion.Listener,
    MovieRepositoryProvider {

    private val networkModule = NetworkModule()
    private val remoteDataSource = RetrofitDataSource(networkModule.api, ImageUrlCreator(networkModule.api))
    private val repoMovies = MovieRepositoryNetwork(remoteDataSource)

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
    override fun goDetail(movie: Movie) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.fragment_container, MovieDetailFragment.create(movie.id))
            .commit()
    }
}