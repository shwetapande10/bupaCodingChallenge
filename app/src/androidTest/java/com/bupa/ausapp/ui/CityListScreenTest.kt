package com.bupa.ausapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.bupa.ausapp.data.City
import com.bupa.ausapp.service.CityService
import com.bupa.ausapp.viewmodel.CityViewModel
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock


@RunWith(AndroidJUnit4::class)
@LargeTest
class CityListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val navController = TestNavHostController(appContext).also {
        it.navigatorProvider.addNavigator(ComposeNavigator())
    }
    private lateinit var viewModel: CityViewModel

    private suspend fun setTestView(composable: @Composable () -> Unit) {
        val mockCityService = mock(CityService::class.java)
        Mockito.`when`(mockCityService.getCities()).thenReturn(listOf(
                City(cityName = "Sydney", latitude = "-33.8678", longitude = "151.2100",
                    country = "Australia", countryAbbr = "AU", state = "New South Wales",
                    capital = "admin", population = "4840600", populationProper = "4840600"
                ), City(
                    cityName = "Melbourne", latitude = "-37.8142", longitude = "144.9631",
                    country = "Australia", countryAbbr = "AU", state = "Victoria",
                    capital = "admin", population = "4529500", populationProper = "4529500")))
        viewModel = CityViewModel(cityService = mockCityService)
        composeTestRule.setContent(composable = composable)
    }

    @Test
    fun `should display all the states`() = runTest {
        setTestView {
            CityListScreen(navController, viewModel)
        }

        composeTestRule.onNodeWithText("Victoria").assertIsDisplayed()
        composeTestRule.onNodeWithText("New South Wales").assertIsDisplayed()
        composeTestRule.onNodeWithText("Melbourne").assertDoesNotExist()
    }

    @Test
    fun `should display Victorian cities when Victoria state header is expanded`() = runTest {
        setTestView {
            CityListScreen(navController, viewModel)
        }
        composeTestRule.onNodeWithText("Victoria").performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Melbourne").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sydney").assertDoesNotExist()
    }

    @Test
    fun `should not display Victorian cities when Victoria state header is collapsed`() = runTest {
        setTestView {
            CityListScreen(navController, viewModel)
        }
        composeTestRule.onNodeWithText("Victoria").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Melbourne").assertIsDisplayed()

        composeTestRule.onNodeWithText("Victoria").performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Melbourne").assertIsNotDisplayed()
    }

    @Test
    fun `should navigate to details page`() = runTest {
        setTestView {
            NavHost(navController, startDestination = "test_start_destination") {
                composable("test_start_destination") {
                    CityListScreen(navController = navController, viewModel = viewModel)
                }
                composable("cityDetail/Melbourne") { }
            }
        }
        composeTestRule.onNodeWithText("Victoria").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Melbourne").assertIsDisplayed()

        composeTestRule.onNodeWithText("Melbourne").performClick()
        composeTestRule.waitForIdle()

        assertTrue(navController.currentBackStackEntry?.destination?.route == "cityDetail/Melbourne")
    }

}
