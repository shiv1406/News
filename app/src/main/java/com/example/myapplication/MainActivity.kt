package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.API.SingltonRetrofitObject
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.models.Articles
import com.example.myapplication.models.NewsModel
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var recyclerView: RecyclerView? = null
    var profileView: ImageView? = null
    var progressBar: ProgressBar? = null
    var user = FirebaseAuth.getInstance().currentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profileView=findViewById(R.id.profile)

        binding.swiperefresh.setOnRefreshListener {
            doApiCall()
            binding.swiperefresh.isRefreshing=false
        }

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchBar.clearFocus()
                performSearch(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })



        if (user!=null)
        {
            Picasso.get().load(user!!.photoUrl)
                .into(profileView)
        }

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progress_circular)
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))
        recyclerView!!.hasFixedSize()
        doApiCall()
    }
    private fun performSearch(Query:String) {
        val videoModelCall: Call<NewsModel> =
            SingltonRetrofitObject.getmInstance()!!.getAPI()!!.getNewsDetails(
                "$Query",
                "2023-01-30", "publishedAt",getString(R.string.newsAPIKey)
            )//news from 30 january to till date
        videoModelCall.enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                setRecyclerView(response.body()!!.getItems())


            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Internet error",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun doApiCall() {
        val index=(0..4).random()
        val queries = arrayOf("India", "Bollywood", "Cricket", "StartUps","Popular news")
        val toSearch=queries[index]
        val videoModelCall: Call<NewsModel> =
            SingltonRetrofitObject.getmInstance()!!.getAPI()!!.getNewsDetails(
                "$toSearch",
                "2023-01-30", "publishedAt",getString(R.string.newsAPIKey)
            )//random changes the date so that new data appear after refreshing
        videoModelCall.enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                setRecyclerView(response.body()!!.getItems())

               // Toast.makeText(this@MainActivity,"$response.body().toString()",Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Internet error",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setRecyclerView(items: Array<Articles>) {
        val myAdapter = MyAdapter(this, items)
        recyclerView!!.adapter = myAdapter
        recyclerView!!.visibility = View.VISIBLE
        progressBar!!.visibility = View.GONE
    }

    fun profile(view: View)
    {
        var i=Intent(this@MainActivity,LoginScreen::class.java)
        startActivity(i)
    }




}