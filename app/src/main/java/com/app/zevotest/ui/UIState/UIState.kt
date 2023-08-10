package com.app.zevotest.ui.UIState

sealed class UIState<out T> {
    object Loading : UIState<Nothing>()

    data class Success<T>(var data: T) : UIState<T>()

    data class Failure(var message: String) : UIState<Nothing>()
}
