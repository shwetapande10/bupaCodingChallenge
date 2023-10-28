package com.bupa.ausapp.data

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("city") val cityName: String,
    @SerializedName("lat") val latitude: String,
    @SerializedName("lng") val longitude: String,
    @SerializedName("iso2") val countryAbbr: String,
    @SerializedName("admin_name") val state: String,
    val country: String,
    val capital: String,
    val population: String,
    val populationProper: String
)
