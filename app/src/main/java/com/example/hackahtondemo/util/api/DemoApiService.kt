package com.example.hackahtondemo.util.api

import com.example.hackahtondemo.util.api.model.CollectionApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface DemoApiService {

    @GET("custom_collections.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    fun getMovies(): Call<CollectionApiResponse>
}