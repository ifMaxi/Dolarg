package com.maxidev.dolararg.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiState(
    val estado: String,
    val aleatorio: Int
)