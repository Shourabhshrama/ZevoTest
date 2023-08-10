package com.app.zevotest.domain.repository


import com.app.zevotest.data.remote.models.Article
import com.app.zevotest.data.remote.models.NewsResponse
import com.app.zevotest.ui.UIState.UIState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface NewsRepository {

    suspend fun getNews(): Flow<UIState<Flow<List<Article>>>>
}