package com.app.zevotest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.app.zevotest.data.remote.models.Article
import com.app.zevotest.domain.usecases.NewsUseCaseImp
import com.app.zevotest.domain.usecases.NewsUseCases
import com.app.zevotest.ui.UIState.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val useCases: NewsUseCaseImp) : ViewModel() {


    private var _postData = MutableStateFlow<UIState<Flow<List<Article>>>>(UIState.Loading)
    val postData: StateFlow<UIState<Flow<List<Article>>>>
        get() = _postData


    init {
        getPost()
    }

    private fun getPost() {
        viewModelScope.launch {
            try {
                var post = useCases.invock()
                _postData.emitAll(post)
            } catch (e: Exception) {
                _postData.value = UIState.Failure(e.message ?: "Some thing went wrong")
            }
        }
    }


}