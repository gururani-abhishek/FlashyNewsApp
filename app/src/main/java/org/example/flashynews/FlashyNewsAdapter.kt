package org.example.flashynews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class FlashyNewsAdapter(val items: ArrayList<String>, private val listener: FlashyNewsItemClicked): RecyclerView.Adapter<FlashyNewsViewHolder>() {

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashyNewsViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent, false)
       val  viewHolder = FlashyNewsViewHolder(view)
       view.setOnClickListener {
           listener.onItemClicked(items[viewHolder.adapterPosition])
       }
       return viewHolder
   }

   override fun onBindViewHolder(holder: FlashyNewsViewHolder, position: Int) {
       val currentItem = items[position]
       holder.titleView.text = currentItem
   }

   override fun getItemCount(): Int {
       return items.size

   }

}

class FlashyNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
   val titleView: TextView = itemView.findViewById(R.id.title)
}


interface FlashyNewsItemClicked {
    fun onItemClicked(item: String)
}