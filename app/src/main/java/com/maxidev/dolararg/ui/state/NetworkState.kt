package com.maxidev.dolararg.ui.state

sealed interface NetworkState {
    data class Success(val success: Any): NetworkState
    data class Error(val message: String): NetworkState
    data object Loading: NetworkState
}