package com.example.myapplication

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ConduitClint {


    val oKHttpBuilder = OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.SECONDS)
        .connectTimeout(2,TimeUnit.SECONDS )

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")

        .addConverterFactory(GsonConverterFactory.create())
        .build()

      val api = retrofit

        .create(Services::class.java)

}