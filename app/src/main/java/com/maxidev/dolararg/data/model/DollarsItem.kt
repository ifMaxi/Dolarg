package com.maxidev.dolararg.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DollarsItem(
    val compra: Double?,
    val venta: Double?,
    val casa: String,
    val nombre: String,
    val moneda: String,
    val fechaActualizacion: String
)