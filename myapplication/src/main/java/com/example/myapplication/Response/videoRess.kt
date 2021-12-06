package com.example.myapplication.Response


import com.google.gson.annotations.SerializedName

data class videoRess(
    @SerializedName("id")val id:Int,
    @SerializedName("results")val results:List<VideoResponse>
)