package com.example.amovies.recycleViewActor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.amovies.R

class RecyclerActorsAdapter: RecyclerView.Adapter<RecyclerActorsAdapter.ViewHolderActor>() {

    private var actors = listOf<ActorItem>()
    class ViewHolderActor(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val imageActor: ImageView = itemView.findViewById(R.id.image_actor)
        private val nameActor: TextView = itemView.findViewById(R.id.name_actor)

        fun bind(actorItem: ActorItem){
            imageActor.setImageResource(actorItem.imageActor)
            nameActor.text = actorItem.nameActor
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderActor {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolderActor(inflater.inflate(R.layout.item_actor, parent, false))
    }

    override fun getItemCount(): Int {
        return actors.size
    }

    override fun onBindViewHolder(holder: ViewHolderActor, position: Int) {
        holder.bind(actors[position])
    }

    fun bindActors(listActors: List<ActorItem>){
        actors = listActors
        notifyDataSetChanged()

    }
}