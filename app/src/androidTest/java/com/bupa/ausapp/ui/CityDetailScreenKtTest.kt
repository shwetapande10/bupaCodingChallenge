package com.bupa.ausapp.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.bupa.ausapp.data.City
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CityDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun cityDetailScreenDisplaysCorrectly() {
        val testCity = City(
            cityName = "Melbourne",
            latitude = "-37.8142",
            longitude = "144.9631",
            country = "Australia",
            countryAbbr = "AU",
            state = "Victoria",
            capital = "admin",
            population = "4529500",
            populationProper = "4529500"
        )

        composeTestRule.setContent {
            CityDetailScreen(city = testCity)
        }

        composeTestRule.onNodeWithText("City: Melbourne (Capital)").assertExists()
        composeTestRule.onNodeWithText("State:").assertExists()
        composeTestRule.onNodeWithText("Victoria").assertExists()
        composeTestRule.onNodeWithText("Population:").assertExists()
        composeTestRule.onNodeWithText("4,529,500").assertExists()
        composeTestRule.onNodeWithTag("cityMap").assertExists()
    }
}
