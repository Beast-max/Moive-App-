package com.example.movieapp

import android.util.Log
import com.example.myapplication.ConduitClint
import com.example.myapplication.Response.GetMoviesResponse
import com.example.myapplication.Response.Movie
import com.example.myapplication.Response.VideoResponse
import com.example.myapplication.Response.videoRess
import com.example.myapplication.Services
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Apiservicesclass {
  private  val api = ConduitClint.api
  
    var string = ""

    fun getPopularMovies(
        page: Int = 1,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: () -> Unit
    ) {
        api.popularmovies(page = page)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
     fun latestMovie(page:Int=1,
                            onSuccess: (movies: List<Movie>) -> Unit,
                            onError: () -> Unit)
    {
api.latestmovie(page = page)
    .enqueue(object :Callback<GetMoviesResponse>{
        override fun onResponse(
            call: Call<GetMoviesResponse>,
            response: Response<GetMoviesResponse>
        ) {
            if(response.isSuccessful)
            {
                val responseBody =response.body()
                if(responseBody!=null)
                {
                    onSuccess.invoke(responseBody.movies)
                }
                else{
                    onError.invoke()
                }
            }
            else{
                onError.invoke()
            }
        }

        override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
            onError.invoke()
        }

    })

    }
     fun top_rated(page:Int = 1, onSuccess: (movies: List<Movie>) -> Unit,
                          onError: () -> Unit)
    {
        api.top_rated(page = page)
            .enqueue(object :Callback<GetMoviesResponse>{
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    if(response.isSuccessful)
                    {
                        var responseBody =response.body()
                        if(responseBody!=null)
                        {
                            onSuccess.invoke(responseBody.movies)
                        }
                        else{
                            onError.invoke()
                        }
                    }
                    else{
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    onError.invoke()
                }

            })

    }
    fun Getvideo(idd:Int,
                 onError: () -> Unit){
        api.movievideo(movie_id = idd)
            .enqueue(object : retrofit2.Callback<videoRess>{
                override fun onResponse(
                    call: Call<videoRess>,
                    response: Response<videoRess>
                ) {
                    if(response.isSuccessful)
                    {
                        var responsebody =response.body()
                        if(responsebody!=null)
                        {
                            if(responsebody.results.isNotEmpty())
                         string = keyy(responsebody.results)
                        }else{
                            onError.invoke()
                        }
                    }else{
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<videoRess>, t: Throwable) {
                    onError.invoke()
                }
            })

    }
    fun keyy(key: List<VideoResponse>):String {
        var video_id: String = ""
        for (i in key.indices) {
            video_id = key[i].toString()
            break
        }
        video_id = video_id.subSequence(18, 29).toString()
        return video_id
    }



}