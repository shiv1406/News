package com.example.myapplication.models

class Default {
    private var totalResults: String? = null

    private lateinit var articles: Array<Articles?>

    private var status: String? = null

    fun getTotalResults(): String? {
        return totalResults
    }

    fun setTotalResults(totalResults: String?) {
        this.totalResults = totalResults
    }

    fun getArticles(): Array<Articles?>? {
        return articles
    }

    fun setArticles(articles: Array<Articles?>) {
        this.articles = articles
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }


}