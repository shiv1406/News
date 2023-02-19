package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityNewsBinding
import com.squareup.picasso.Picasso

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val space="  "
        val msg=intent.extras?.getString("title")
        val url=intent.extras?.getString("img_url")
        val description=intent.extras?.getString("description")
        val content=intent.extras?.getString("content")

        Picasso.get().load(url).into(binding.newsImg)
        binding.newsTitle.text=msg
        binding.newsDescription.text=space+description
        binding.newsContent.text=content
    }
}