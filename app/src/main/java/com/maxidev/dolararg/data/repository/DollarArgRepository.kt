package com.maxidev.dolararg.data.repository

import com.maxidev.dolararg.data.model.ApiState
import com.maxidev.dolararg.data.model.DollarsItem

interface DollarArgRepository {
    suspend fun getAllTypeOfDollars(): List<DollarsItem>

    suspend fun getApiState(): ApiState
}