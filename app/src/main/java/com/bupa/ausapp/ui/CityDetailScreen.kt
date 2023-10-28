package com.bupa.ausapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bupa.ausapp.data.City
import com.bupa.ausapp.ui.components.CityMap
import com.bupa.ausapp.ui.components.LabelValue
import java.text.NumberFormat

@Composable
fun CityDetailScreen(city: City) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "City: ${city.cityName} ${capital(city)}",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Divider()
        Spacer(modifier = Modifier.height(16.dp))

        LabelValue("State:", city.state)
        LabelValue("Population:", formatNumber(city.population))

        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))
        CityMap(latitude = city.latitude.toDouble(), longitude = city.longitude.toDouble())
    }
}

private fun capital(city: City) = if (city.capital.isNotBlank()) "(Capital)" else ""

private fun formatNumber(num : String) = NumberFormat.getNumberInstance().format(num.toLong())

