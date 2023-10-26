package com.maxidev.dolararg.ui.presentation

import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxidev.dolararg.data.model.DollarsItem
import com.maxidev.dolararg.ui.state.NetworkState
import com.maxidev.dolararg.ui.viewmodel.DollarArgViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(api = 0)
@Composable
fun DollarsScreen(
    modifier: Modifier = Modifier,
    viewModel: DollarArgViewModel = hiltViewModel()
) {
    NetworkCheck(
        modifier = modifier,
        status = viewModel.netState
    )
}

@RequiresApi(api = 0)
@Suppress("UNCHECKED_CAST")
@Composable
private fun NetworkCheck(modifier: Modifier = Modifier, status: NetworkState) {
    when (status) {
        is NetworkState.Success -> ListOfDollars(
            modifier = modifier,
            dollar = status.success as List<DollarsItem>
        )
        is NetworkState.Loading -> FakeLoadingScreen(modifier = modifier)
        is NetworkState.Error -> FakeErrorScreen(modifier = modifier)
    }
}

@RequiresApi(api = 0)
@Composable
private fun ListOfDollars(modifier: Modifier = Modifier, dollar: List<DollarsItem>) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(dollar) { usd ->
            ScreenContent(dollar = usd)
        }

        item {
            UpdateDayComponent(dollar = dollar[5])
        }
    }
}

@Composable
private fun ScreenContent(modifier: Modifier = Modifier, dollar: DollarsItem) {
    Card(
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier
            .height(110.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(all = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(50.dp)
        ) {
            DollarName(
                name = dollar.nombre,
                type = dollar.moneda
            )
            DollarValue(
                buy = dollar.compra.toString(),
                sell = dollar.venta.toString()
            )
        }
    }
}

@RequiresApi(api = 0)
@Composable
private fun UpdateDayComponent(dollar: DollarsItem) {
    val dateAndHour = LocalDateTime.parse(
        dollar.fechaActualizacion,
        DateTimeFormatter.ISO_DATE_TIME
    )
    val datePattern = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
    val formattedHour = dateAndHour.format(datePattern)

    UpdateDay(day = formattedHour)
}

@Composable
private fun DollarName(name: String, type: String) {
    Column(
        modifier = Modifier
            .padding(10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = name
                .replace("Contado con liquidación", "CCL")
                .replace("Solidario (Turista)", "Turista"),
            modifier = Modifier.width(138.dp),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = type,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun DollarValue(buy: String?, sell: String?) {
    Column(
        modifier = Modifier
            .padding(10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = "Compra: $buy"
        )
        Text(
            text = "Venta: $sell"
        )
    }
}

@Composable
private fun UpdateDay(day: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Fecha de actualización: $day"
        )
    }
}