package com.example.amovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.amovies.fragments.MovieDetailFragment
import com.example.amovies.fragments.RootFragment

class MainActivity : AppCompatActivity(), MovieDetailFragment.Companion.Listener {

    private val rootFragment = RootFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null)
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, rootFragment).commit()

    }

    override fun goBack() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, rootFragment).commit()
    }

/*    override fun goDetail() {
        supportFragmentManager.beginTransaction().addToBackStack(null).add(R.id.fragment_container, movieDetailFragment).commit()
    }*/
}