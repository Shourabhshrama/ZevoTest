package com.app.zevotest.domain.repository


import com.app.zevotest.data.local.dao.NewsDao
import com.app.zevotest.data.remote.datasource.RemoteDataSource
import com.app.zevotest.data.remote.datasource.RemoteDataSourceImp
import com.app.zevotest.data.remote.models.Article

import com.app.zevotest.ui.UIState.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSourceImp,
    private val localDataStore: NewsDao
) : NewsRepository {
    override suspend fun getNews(): Flow<UIState<Flow<List<Article>>>> {
        return flow {
            emit(UIState.Loading)

            try {
                val articles = remoteDataSource.getNews()
                localDataStore.insertLit(articles)
                var cacheData = localDataStore.getArticle()
                emit(UIState.Success(cacheData))
            } catch (e: Exception) {


                emit(UIState.Failure(e.message ?: "Something went Wrong "))

            }
        }.flowOn(Dispatchers.IO)

    }
}