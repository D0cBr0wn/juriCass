package com.example.juricass.homeScreen

import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.juricass.ui.homeScreen.HomeScreen
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.test.core.app.ApplicationProvider
import com.example.juricass.JuriCassRoutes
import com.example.juricass.MainActivity
import com.example.juricass.assertCurrentRouteName
import com.example.juricass.data.fixtures.SearchPageFixture
import com.example.juricass.data.model.SearchPage
import com.example.juricass.data.model.SearchQuery
import com.example.juricass.data.model.SearchResult
import com.example.juricass.data.state.DecisionState
import com.example.juricass.data.state.HomeState
import com.example.juricass.ui.decisionScreen.DecisionScreen
import com.example.juricass.ui.decisionScreen.DecisionViewModel
import com.example.juricass.ui.decisionScreen.DecisionViewModelFactory
import org.junit.Assert
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        // Create a TestNavHostController
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        // Set the navigator provider to use the ComposeNavigator
        navController.navigatorProvider.addNavigator(ComposeNavigator())
    }

    @Test
    fun check_startDestination() {
        var homeState = HomeState()

        composeTestRule.setContent {
            NavHost(navController = navController, startDestination = JuriCassRoutes.HOME.name) {
                composable(route = JuriCassRoutes.HOME.name) {
                    HomeScreen(homeState, navController, {})
                }
            }
        }

        navController.assertCurrentRouteName(JuriCassRoutes.HOME.name)
    }


    @Test
    fun home_search_is_null() {
        var homeState = HomeState()

        composeTestRule.setContent {
            NavHost(navController = navController, startDestination = JuriCassRoutes.HOME.name) {
                composable(route = JuriCassRoutes.HOME.name) {
                    HomeScreen(homeState, navController, {})
                }
            }
        }

        composeTestRule.onNodeWithTag("homeTopBar").assertIsDisplayed()
        composeTestRule.onNodeWithTag("skeletonLoader").assertDoesNotExist()
        composeTestRule.onNodeWithText("No result found").assertIsDisplayed()
    }

    @Test
    fun home_search_is_loading() {
        var homeState = HomeState(isLoading = true)

        composeTestRule.setContent {
            NavHost(navController = navController, startDestination = JuriCassRoutes.HOME.name) {
                composable(route = JuriCassRoutes.HOME.name) {
                    HomeScreen(homeState, navController, {})
                }
            }
        }

        composeTestRule.onNodeWithTag("homeTopBar").assertIsDisplayed()
        composeTestRule.onNodeWithTag("skeletonLoader").assertIsDisplayed()
        composeTestRule.onNodeWithText("No result found").assertDoesNotExist()
    }

    @Test
    fun home_search_is_not_null_no_results() {
        var homeState = HomeState(
            searchPage = SearchPageFixture.searchPageNoResults()
        )

        composeTestRule.setContent {
            HomeScreen(homeState, navController, {})//
        }
        composeTestRule.onNodeWithTag("homeTopBar").assertIsDisplayed()
        composeTestRule.onNodeWithText("No result found").assertIsDisplayed()
    }

    @Test
    fun home_search_is_not_null_results() {
        var homeState = HomeState(
            searchPage = SearchPageFixture.searchPage()
        )

        composeTestRule.setContent {
            NavHost(navController = navController, startDestination = JuriCassRoutes.HOME.name) {
                composable(route = JuriCassRoutes.HOME.name) {
                    HomeScreen(homeState, navController, {})
                }
                composable(route = JuriCassRoutes.DECISION.name + "/{decisionId}",
                    arguments = listOf(navArgument("decisionId") { type = NavType.StringType })
                ) { navBackStackEntry ->
                    val decisionId = navBackStackEntry.arguments?.getString("decisionId")

                    DecisionScreen(
                        state = DecisionState(),
                        navController = navController
                    )
                }
            }
        }
        composeTestRule.onNodeWithTag("homeTopBar").assertIsDisplayed()
        composeTestRule.onNodeWithTag("homeLazyColumn").assertIsDisplayed()
        composeTestRule.onNodeWithTag("decisionButton").assertIsDisplayed()
        composeTestRule.onNodeWithTag("decisionButton").performClick()
        navController.assertCurrentRouteName(JuriCassRoutes.DECISION.name + "/{decisionId}")//TODO: find a way to work with proper Id
    }
}
