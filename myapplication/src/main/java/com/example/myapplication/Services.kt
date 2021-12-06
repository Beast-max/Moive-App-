package com.example.myapplication

import com.example.myapplication.Response.GetMoviesResponse
import com.example.myapplication.Response.videoRess
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Services {

    @GET("movie/popular")
         fun popularmovies(@Query("api_key") api_key:String="bbb9a0817f99778f3338c68e75c866bc",
                              @Query("page") page:Int):Call<GetMoviesResponse>
    @GET("movie/upcoming")
     fun latestmovie(@Query("api_key")api_key: String="bbb9a0817f99778f3338c68e75c866bc",
                     @Query("page") page: Int   ):Call<GetMoviesResponse>
    @GET("movie/top_rated")
     fun top_rated(@Query("api_key")api_key: String="bbb9a0817f99778f3338c68e75c866bc",
                          @Query("page") page: Int):Call<GetMoviesResponse>
     @GET("movie/{movie_id}/videos")
     fun movievideo(@Path("movie_id")movie_id:Int, @Query("api_key")api_key: String="bbb9a0817f99778f3338c68e75c866bc"):Call<videoRess>
}