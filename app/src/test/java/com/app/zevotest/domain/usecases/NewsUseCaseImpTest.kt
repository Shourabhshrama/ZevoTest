package com.app.zevotest.domain.usecases

import com.app.zevotest.data.remote.models.Article
import com.app.zevotest.domain.repository.NewsRepositoryImp
import com.app.zevotest.ui.UIState.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class NewsUseCaseImpTest {

    private lateinit var newsUseCase: NewsUseCaseImp
    private lateinit var repository: NewsRepositoryImp

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        repository = mock()
        newsUseCase = NewsUseCaseImp(repository)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `invock returns Flow of UIState when repository returns Flow of data`() = runTest {
        val articles = listOf( Article(
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
        val flowResult = flowOf(UIState.Success(articles))

        whenever(repository.getNews()).thenReturn(flowResult)

        val resultFlow = newsUseCase.invock()

        assertEquals(UIState.Success(articles), resultFlow.first())
    }

    @Test
    fun `invock returns Flow of UIState when repository returns Flow of error`() = runTest {
        val errorState = UIState.Failure("An error occurred")
        val flowResult = flowOf(errorState)

        whenever(repository.getNews()).thenReturn(flowResult)

        val resultFlow = newsUseCase.invock()

        assertEquals(errorState, resultFlow.first())
    }
}