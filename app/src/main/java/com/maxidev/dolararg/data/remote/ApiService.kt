package com.maxidev.dolararg.data.remote

import com.maxidev.dolararg.data.model.ApiState
import com.maxidev.dolararg.data.model.DollarsItem
import com.maxidev.dolararg.utils.Constants.API_STATE
import com.maxidev.dolararg.utils.Constants.DOLLARS
import retrofit2.http.GET

interface ApiService {
    @GET(DOLLARS)
    suspend fun getAllTypeOfDollars(): List<DollarsItem>

    @GET(API_STATE)
    suspend fun getApiState(): ApiState
}