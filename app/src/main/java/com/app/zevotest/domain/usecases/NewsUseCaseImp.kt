package com.app.zevotest.domain.usecases


import com.app.zevotest.data.remote.models.Article

import com.app.zevotest.domain.repository.NewsRepositoryImp
import com.app.zevotest.ui.UIState.UIState
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class NewsUseCaseImp @Inject constructor(private val repository: NewsRepositoryImp) : NewsUseCases {
    override suspend fun invock(): Flow<UIState<List<Article>>> {
        return repository.getNews()
    }
}