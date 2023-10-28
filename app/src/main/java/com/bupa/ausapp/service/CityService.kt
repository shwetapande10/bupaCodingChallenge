package com.bupa.ausapp.service

import android.content.Context
import  com.bupa.ausapp.data.City
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CityService {
    suspend fun getCities(): List<City>
}

class AusCityService(private val context: Context): CityService {
    override suspend fun getCities(): List<City> {
        return withContext(Dispatchers.IO) {
            val jsonString = context.assets.open("au_cities.json").bufferedReader().use {
                it.readText()
            }
            val gson = Gson()
            val cityListType = object : TypeToken<List<City>>() {}.type
            gson.fromJson(jsonString, cityListType)
        }
    }
}
