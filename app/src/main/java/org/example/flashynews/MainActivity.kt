package org.example.flashynews

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.example.sharememes.MySingleton

class MainActivity : AppCompatActivity(), FlashyNewsItemClicked {
    //we used m to indicate that it's a member variable...A member variable can be accessed from multiple places.
    private lateinit var mAdapter: FlashyNewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        fetch()
        mAdapter =  FlashyNewsAdapter(this)
        recyclerView.adapter = mAdapter

    }

    private fun fetch() {
        // val url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=5fe8a9c7c69049b1941bba53eb135bc9"
        val url = "https://saurav.tech/NewsAPI/top-headlines/category/sports/in.json"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                val newsArray = ArrayList<News>() //this will hold things that I req, like : title, author, date, etc, etc...
                val newsJsonArray = it.getJSONArray("articles") //this will hold array I got from calling the news org API
                //accessing all individual elements of the newsJsonArray(articles)...
                for(i in 0 until newsJsonArray.length()) {
                   val newsjsonObject = newsJsonArray.getJSONObject(i)
                   val news = News(
                       newsjsonObject.getString("title"),
                       newsjsonObject.getString("author"),
                       newsjsonObject.getString("url"),
                       newsjsonObject.getString("urlToImage")
                   )
                    newsArray.add(news)
                }
                mAdapter.updateNews(newsArray)
            },
            {
                Toast.makeText(this, "Error in the hood boys!", Toast.LENGTH_SHORT).show()
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {
        val builder = CustomTabsIntent.Builder();
        val customTabsIntent  = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(item.url));
    }
}