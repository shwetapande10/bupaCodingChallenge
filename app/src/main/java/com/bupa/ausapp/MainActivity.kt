package com.bupa.ausapp


import com.bupa.ausapp.ui.CityDetailScreen
import com.bupa.ausapp.ui.CityListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import  com.bupa.ausapp.ui.theme.AusAppTheme
import  com.bupa.ausapp.viewmodel.CityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: CityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AusAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "cityList") {
                        composable("cityList") {
                            CityListScreen(navController, viewModel)
                        }
                        composable("cityDetail/{cityName}") { backStackEntry ->
                            val cityName = backStackEntry.arguments?.getString("cityName")
                            viewModel.cities.value?.find { it.cityName == cityName }?.let {
                                CityDetailScreen(it)
                            }
                        }
                    }
                }
            }
        }
    }
}

