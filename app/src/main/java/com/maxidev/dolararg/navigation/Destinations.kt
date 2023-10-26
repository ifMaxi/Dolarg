package com.maxidev.dolararg.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Api
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.ui.graphics.vector.ImageVector
import com.maxidev.dolararg.R

sealed class Destinations(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector?
) {
    // Principal screens
    data object AllDollars: Destinations(
        route = "all_dollars",
        resourceId = R.string.all_dollar,
        icon = Icons.Outlined.AttachMoney
    )
    data object ApiState: Destinations(
        route = "api_state",
        resourceId = R.string.api_state,
        icon = Icons.Outlined.Api
    )
}