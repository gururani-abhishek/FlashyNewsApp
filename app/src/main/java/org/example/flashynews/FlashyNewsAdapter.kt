package org.example.flashynews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class FlashyNewsAdapter( private val listener: FlashyNewsItemClicked): RecyclerView.Adapter<FlashyNewsViewHolder>() {
   private val items: ArrayList<News> = ArrayList()
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
       holder.titleView.text = currentItem.title
   }

   override fun getItemCount(): Int {
       return items.size

   }
    fun updateNews(updatedNews : ArrayList<News>) {
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }

}

class FlashyNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
   val titleView: TextView = itemView.findViewById(R.id.title)
}


interface FlashyNewsItemClicked {
    fun onItemClicked(item: News)
}