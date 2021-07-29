package org.example.flashynews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), FlashyNewsItemClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val items = fetch()
        val adapter: FlashyNewsAdapter = FlashyNewsAdapter(items, this)
        recyclerView.adapter = adapter

    }

    private fun fetch() : ArrayList<String> {
        val list = ArrayList<String>()
        for(i in 0 until 100) {
            list.add("item $i")
        }
        return list
    }

    override fun onItemClicked(item: String) {
       Toast.makeText(this, "clicked item is $item", Toast.LENGTH_SHORT).show()
    }
}