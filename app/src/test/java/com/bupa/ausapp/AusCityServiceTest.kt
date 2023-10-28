package com.bupa.ausapp

import android.content.Context
import android.content.res.AssetManager
import com.bupa.ausapp.service.AusCityService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.io.ByteArrayInputStream

@RunWith(MockitoJUnitRunner::class)
class AusCityServiceTest {

    @Test
    fun testGetCities() = runBlocking {
        val jsonString = """
            [   {
                 "city": "Sydney", 
                 "lat": "-33.8678", 
                 "lng": "151.2100", 
                 "country": "Australia", 
                 "iso2": "AU", 
                 "admin_name": "New South Wales", 
                 "capital": "admin", 
                 "population": "4840600", 
                 "population_proper": "4840600"
               }, {
                 "city": "Melbourne", 
                 "lat": "-37.8142", 
                 "lng": "144.9631", 
                 "country": "Australia", 
                 "iso2": "AU", 
                 "admin_name": "Victoria", 
                 "capital": "admin", 
                 "population": "4529500", 
                 "population_proper": "4529500"
                }
            ]""".trimIndent()
        val context = mock(Context::class.java)
        val assets = mock(AssetManager::class.java)
        val inputStream = ByteArrayInputStream(jsonString.toByteArray(Charsets.UTF_8))
        `when`(context.assets).thenReturn(assets)
        `when`(assets.open(anyString())).thenReturn(inputStream)

        val cityService = AusCityService(context)
        val cities = cityService.getCities()

        assertEquals(2, cities.size)
        assertEquals("Sydney", cities.first().cityName)
        assertEquals("New South Wales", cities.first().state)
        assertEquals("Melbourne", cities.last().cityName)
        assertEquals("Victoria", cities.last().state)
    }
}
