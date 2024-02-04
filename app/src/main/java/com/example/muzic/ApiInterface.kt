package com.example.muzic

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers("X-RapidAPI-Key: f88f9e9adamshdd8b8c2e907c031p1292d9jsn4d7c336e8c5e ","X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query:String): Call<MyData>
}