package com.app.zevotest.data.remote.datasource

import com.app.zevotest.API_KEY
import com.app.zevotest.COUNTRY
import com.app.zevotest.COUNTRY_NAME
import com.app.zevotest.data.remote.api.ApiService
import com.app.zevotest.data.remote.models.Article
import com.app.zevotest.data.remote.models.NewsResponse
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response


class RemoteDataSourceImpTest {

    private lateinit var remoteDataSource: RemoteDataSourceImp
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        apiService = mock()
        remoteDataSource = RemoteDataSourceImp(apiService)
    }

    @Test
    fun `getNews returns list of articles on successful response`() = runTest {
        val articles = listOf(
            Article(
                1,
                "test 1",
                "test content",
                "test des",
                "test publised",
                "test title",
                "",
                "test"
            ),
            Article(
                1,
                "test 1",
                "test content",
                "test des",
                "test publised",
                "test title",
                "",
                "test"
            ),
            Article(
                1,
                "test 1",
                "test content",
                "test des",
                "test publised",
                "test title",
                "",
                "test"
            )
        )
        var responseList = NewsResponse(articles, "SUCCESS", 4)
        val response = Response.success(responseList)

        whenever(apiService.getNews(COUNTRY_NAME, API_KEY)).thenReturn(response)

        val result = remoteDataSource.getNews()


        assertEquals(articles, result)
        assertEquals(3, result.size)
    }

    @Test
    fun `getNews returns empty list on unsuccessful response`() = runTest {
        val response = Response.error<NewsResponse>(404, mock())

        whenever(apiService.getNews(COUNTRY_NAME, API_KEY)).thenReturn(response)

        val result = remoteDataSource.getNews()

        assertEquals(emptyList<Article>(), result)
    }

    @Test
    fun `getNews returns empty list on exception`() = runTest {
        whenever(apiService.getNews(COUNTRY_NAME, API_KEY)).thenAnswer { throw Exception() }

        val result = remoteDataSource.getNews()

        assertEquals(emptyList<Article>(), result)
    }
}