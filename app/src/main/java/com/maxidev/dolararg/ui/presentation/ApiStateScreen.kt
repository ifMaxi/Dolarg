package com.maxidev.dolararg.ui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxidev.dolararg.data.model.ApiState
import com.maxidev.dolararg.ui.state.NetworkState
import com.maxidev.dolararg.ui.viewmodel.ApiStateViewModel

@Composable
fun ApiStateScreen(
    modifier: Modifier = Modifier,
    viewModel: ApiStateViewModel = hiltViewModel()
) {
    NetworkCheck(
        modifier = modifier,
        status = viewModel.netState
    )
}

@Composable
private fun NetworkCheck(modifier: Modifier = Modifier, status: NetworkState) {
    when (status) {
        is NetworkState.Success -> ApiStateContent(apiState = status.success as ApiState)
        is NetworkState.Loading -> FakeLoadingScreen(modifier = modifier)
        is NetworkState.Error -> FakeErrorScreen(modifier = modifier)
    }
}

@Composable
private fun ApiStateContent(apiState: ApiState) {
    ServerStatusAndRandomNumber(state = apiState.estado)
}

@Composable
private fun ServerStatusAndRandomNumber(state: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Estado actual de la Api:",
            style = MaterialTheme.typography.headlineLarge,
        )
        Text(
            text = state,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}