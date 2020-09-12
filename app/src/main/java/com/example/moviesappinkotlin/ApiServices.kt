package com.example.moviesappinkotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey : String = "2924d6d01afa790692111622545966ac",
        @Query("page") pageNumber: Int = 1
    ) : Call<MoviesResponse>
}