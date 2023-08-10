package com.app.zevotest.domain.repository



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
    private val remoteDataSource: RemoteDataSourceImp

) : NewsRepository {
    override suspend fun getNews(): Flow<UIState<List<Article>>> {
        return flow {
            emit(UIState.Loading)

            try {
                val articles = remoteDataSource.getNews()
                // localDataSource.insertArticles(articles) // Cache articles locally
                //  val cachedArticles = localDataSource.getArticles()
                emit(UIState.Success(articles))
            } catch (e: Exception) {
                //  val cachedArticles = localDataSource.getArticles()
//                if (cachedArticles.isNotEmpty()) {
//                    emit(UIState.Success(cachedArticles))
//                } else {
                emit(UIState.Failure(e.message ?: "Something went Wrong "))
                // }
            }
        }.flowOn(Dispatchers.IO)

    }
}