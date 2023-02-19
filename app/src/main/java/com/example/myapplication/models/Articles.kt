package com.example.myapplication.models

class Articles {
    var publishedAt: String? = null
    var author: String? = null
    var urlToImage: String? = null
    var description: String? = null
    private var source: Source? = null
    var title: String? = null
    var url: String? = null
    var content: String? = null

    fun getSource(): Source? {
        return source
    }

    fun setSource(source: Source?) {
        this.source = source
    }


}
