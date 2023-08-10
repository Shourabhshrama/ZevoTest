package com.app.zevotest.data.remote.api

import com.app.zevotest.APIKEY
import com.app.zevotest.COUNTRY
import com.app.zevotest.data.remote.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")

    suspend fun getNews(
        @Query(COUNTRY) country: String,
        @Query(APIKEY) apiKey: String
    ): Response<NewsResponse>
}