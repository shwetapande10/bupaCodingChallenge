package com.bupa.ausapp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bupa.ausapp.data.City
import com.bupa.ausapp.ui.components.CollapsibleSection
import com.bupa.ausapp.viewmodel.CityViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CityListScreen(navController: NavController, viewModel: CityViewModel) {
    val cities by viewModel.cities.observeAsState(emptyList())
    val expandedStates = remember { mutableStateMapOf<String, Boolean>() }
    Surface(color = MaterialTheme.colorScheme.background) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val groupedCities = cities.groupBy { it.state }
            groupedCities.keys.forEach { state ->
                if (state !in expandedStates) {
                    expandedStates[state] = false
                }
            }
            groupedCities.forEach { (state, cityList) ->
                val isExpanded = expandedStates[state] ?: false
                stickyHeader {
                    CollapsibleSection(header = state, isExpanded = isExpanded) {
                        expandedStates[state] = !isExpanded
                    }
                }
                if (isExpanded) {
                    item {
                        cityList.forEach { city ->
                            CityItem(navController, city)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CityItem(navController: NavController, cityInfo: City) {
    ListItem(
        modifier = Modifier
            .clickable {
                navController.navigate("cityDetail/${cityInfo.cityName}")
            }
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(2.dp)
            .border(0.5.dp, MaterialTheme.colorScheme.secondary, RoundedCornerShape(4.dp)),
        headlineText = {
            Text(
                text = cityInfo.cityName,
                style = MaterialTheme.typography.bodySmall
            )
        }
    )
}

