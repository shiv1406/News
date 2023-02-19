package com.example.myapplication.API

import com.example.myapplication.models.NewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface API {
    @GET("everything")
    fun getNewsDetails(
        @Query("q") q: String?,
        @Query("from") from: String?,
        @Query("sortBy") sortBy: String?,
        @Query("apiKey") apiKey: String?,
    ): Call<NewsModel>

}