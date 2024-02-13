package com.example.amovies.movieDetail

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.amovies.R
import com.example.amovies.model.Actor

class RecyclerActorsAdapter: ListAdapter<Actor, RecyclerActorsAdapter.ViewHolderActor>(DiffCallback()) {
    class ViewHolderActor(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val imageActor: ImageView = itemView.findViewById(R.id.image_actor)
        private val nameActor: TextView = itemView.findViewById(R.id.name_actor)

        fun bind(actorItem: Actor){
            nameActor.text = actorItem.name
            Log.d("ActorAdapterLog", actorItem.imageUrl.toString())
            imageActor.load(actorItem.imageUrl){
                placeholder(R.drawable.download_img)
                error(R.drawable.download_error)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Actor>() {
        override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderActor {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolderActor(inflater.inflate(R.layout.item_actor, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderActor, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}