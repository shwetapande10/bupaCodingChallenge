package com.bupa.ausapp.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun CityMap(latitude: Double, longitude: Double) {
    val city = LatLng(latitude, longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(city, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize().testTag("cityMap"),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = city)
        )
    }
}