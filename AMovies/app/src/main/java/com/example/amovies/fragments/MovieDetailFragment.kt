package com.example.amovies.fragments

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.amovies.R
import java.io.Serializable

class MovieDetailFragment: Fragment() {

    private var listener: Listener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.back_tv).setOnClickListener {
            Log.d("CheckStatusListener", "MovieDetailFragment::: ${listener.toString()}")
            listener?.goBack()
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

/*    fun setListener(l: Listener){
        listener = l
    }*/

    companion object{
        interface Listener{
            fun goBack()
        }
    }
}