package com.example.amovies.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.amovies.R

class RootFragment : Fragment() {

    private var listener: Listener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_root, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().supportFragmentManager.beginTransaction().add(R.id.preview_movie, MovieListFragment()).commit()

        view.findViewById<FrameLayout>(R.id.preview_movie).setOnClickListener {
            Log.d("CheckStatusListener", "RootFragment::: ${listener.toString()}")
            listener?.goDetail()
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
            fun goDetail()
        }
    }
}