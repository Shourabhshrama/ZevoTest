package com.app.zevotest.data.remote.datasource


import com.app.zevotest.data.remote.models.Article


interface RemoteDataSource {

    suspend fun getNews(): List<Article>
}