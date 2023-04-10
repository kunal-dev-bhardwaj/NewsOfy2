package com.example.newsofy

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=in&apiKey=3d8b574c264244078ecfe8271ace0949

const val BASE_URL = "https://newsapi.org/" //this is base url which is common
const val API_KEY = "3d8b574c264244078ecfe8271ace0949" //this is api key

interface NewsInterface {
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadLines(@Query("country") country: String,
                     @Query("page") page: Int): Call<News>

}
object NewsService{
    fun getInstance():Retrofit{
        return  Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
           .build()

    }

}
