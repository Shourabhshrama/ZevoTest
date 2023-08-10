package com.app.zevotest.data.remote.datasource

import com.app.zevotest.API_KEY
import com.app.zevotest.COUNTRY_NAME

import com.app.zevotest.data.remote.api.ApiService
import com.app.zevotest.data.remote.models.Article

import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService

) : RemoteDataSource {
    override suspend fun getNews(): List<Article> {
        try {
            val response = apiService.getNews(COUNTRY_NAME, API_KEY)
            if (response.isSuccessful) {
                val articles = response.body()?.articles ?: emptyList()

                return articles
            } else {
                return emptyList()
            }
        } catch (e: Exception) {


            return emptyList()
        }
    }
}