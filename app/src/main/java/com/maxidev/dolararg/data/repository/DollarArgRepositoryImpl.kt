package com.maxidev.dolararg.data.repository

import com.maxidev.dolararg.data.model.ApiState
import com.maxidev.dolararg.data.model.DollarsItem
import com.maxidev.dolararg.data.remote.ApiService
import javax.inject.Inject

class DollarArgRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): DollarArgRepository {
    override suspend fun getAllTypeOfDollars(): List<DollarsItem> =
        apiService.getAllTypeOfDollars()

    override suspend fun getApiState(): ApiState =
        apiService.getApiState()
}