package com.maxidev.dolararg.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.dolararg.data.repository.DollarArgRepositoryImpl
import com.maxidev.dolararg.ui.state.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ApiStateViewModel @Inject constructor(
    private val repository: DollarArgRepositoryImpl
): ViewModel() {
    var netState: NetworkState by mutableStateOf(NetworkState.Loading)
        private set

    init {
        apiState()
    }

    private fun apiState() {
        viewModelScope.launch {
            netState = NetworkState.Loading
            netState = try {
                NetworkState.Success(repository.getApiState())
            } catch (ioException: IOException) {
                NetworkState.Error("Unexpected error")
            } catch (http: Error) {
                NetworkState.Error("Http error")
            }
        }
    }
}