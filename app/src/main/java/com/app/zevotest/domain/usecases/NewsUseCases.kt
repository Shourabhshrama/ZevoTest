package com.app.zevotest.domain.usecases

//import com.app.zevotest.data.local.entity.NewsArticleEntity
import com.app.zevotest.data.remote.models.Article
import com.app.zevotest.data.remote.models.NewsResponse
import com.app.zevotest.ui.UIState.UIState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface NewsUseCases {

    suspend fun invock(): Flow<UIState<List<Article>>>
}