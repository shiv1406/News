package com.example.myapplication.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SingltonRetrofitObject private constructor() {
    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getAPI(): API? {
        return retrofit.create(API::class.java)
    }

    companion object {
        private var mInstance: SingltonRetrofitObject? = null
        private const val BASE_URL = "https://newsapi.org/v2/"
        private lateinit var retrofit:Retrofit

        @Synchronized
        fun getmInstance(): SingltonRetrofitObject? {
            if (mInstance == null) {
                mInstance = SingltonRetrofitObject()
            }
            return mInstance
        }
    }
}
