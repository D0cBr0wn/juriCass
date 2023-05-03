package com.example.juricass.decisionScreen


import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.NavType
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.juricass.JuriCassRoutes
import com.example.juricass.data.fixtures.DecisionFixture
import com.example.juricass.data.fixtures.SearchPageFixture
import com.example.juricass.data.state.DecisionState
import com.example.juricass.data.state.HomeState
import com.example.juricass.ui.decisionScreen.DecisionScreen
import com.example.juricass.ui.homeScreen.HomeScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DecisionScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController
    private lateinit var state: DecisionState

    @Before
    fun setup() {
        // Create a TestNavHostController
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        // Set the navigator provider to use the ComposeNavigator
        navController.navigatorProvider.addNavigator(ComposeNavigator())

        var homeState = HomeState(
            searchPage = SearchPageFixture.searchPage()
        )

        composeTestRule.setContent {
            NavHost(navController = navController, startDestination = JuriCassRoutes.HOME.name) {
                composable(route = JuriCassRoutes.HOME.name) {
                    HomeScreen(
                        homeState,
                        navController,
                        homeSearch = { }
                    )
                }
                composable(route = JuriCassRoutes.DECISION.name + "/{decisionId}",
                    arguments = listOf(navArgument("decisionId") { type = NavType.StringType })
                ) {
                    DecisionScreen(
                        state = state,
                        navController = navController
                    )
                }
            }
        }
    }




    @Test
    fun decision_is_null() {
        state = DecisionState()
        navigateToDecisionScreen()

        composeTestRule.onNodeWithTag("genericTopBar").assertIsDisplayed()
        composeTestRule.onNodeWithTag("nothingFound").assertIsDisplayed()
    }

    @Test
    fun decision_is_loading() {
        state = DecisionState(isLoading = true)
        navigateToDecisionScreen()

        composeTestRule.onNodeWithTag("genericTopBar").assertIsDisplayed()
        composeTestRule.onNodeWithTag("circleLoader").assertIsDisplayed()
        composeTestRule.onNodeWithTag("nothingFound").assertDoesNotExist()
    }

    @Test
    fun decision_is_not_null_no_zones() {
        state = DecisionState(
            decision = DecisionFixture.noZonesDecision()
        )

        navigateToDecisionScreen()
        composeTestRule.onNodeWithTag("genericTopBar").assertIsDisplayed()
        composeTestRule.onNodeWithTag("solutionDisplayer").assertIsDisplayed()
        composeTestRule.onNodeWithTag("noZones").assertIsDisplayed()
    }

    @Test
    fun decision_is_not_null_with_zones() {
        state = DecisionState(
            decision = DecisionFixture.decision()
        )

        navigateToDecisionScreen()
        composeTestRule.onNodeWithTag("genericTopBar").assertIsDisplayed()
        composeTestRule.onNodeWithTag("solutionDisplayer").assertIsDisplayed()
        composeTestRule.onNodeWithTag("zoneDisplayer").assertIsDisplayed()
    }

    private fun navigateToDecisionScreen() {
        composeTestRule.onNodeWithTag("decisionButton").performClick()
    }
}