package com.example.myapplication.models

class NewsModel {

    private lateinit var articles: Array<Articles>

    fun getItems(): Array<Articles> {
        return articles
    }

}
