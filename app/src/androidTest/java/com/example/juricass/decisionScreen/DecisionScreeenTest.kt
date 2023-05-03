package com.example.juricass.decisionScreen

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.juricass.JuriCassRoutes
import com.example.juricass.assertCurrentRouteName
import com.example.juricass.data.model.Decision
import com.example.juricass.data.model.SearchPage
import com.example.juricass.data.model.SearchQuery
import com.example.juricass.data.model.SearchResult
import com.example.juricass.data.model.ZoneSegment
import com.example.juricass.data.state.DecisionState
import com.example.juricass.data.state.HomeState
import com.example.juricass.ui.decisionScreen.DecisionScreen
import com.example.juricass.ui.homeScreen.HomeScreen
import com.example.juricass.ui.homeScreen.HomeViewModel
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
            searchPage = SearchPage(
                page= 0,
                pageSize= 10,
                query = SearchQuery(
                    query= "propriété",
                    resolveReferences =  true
                ),
                total =  10000,
                previousPage =  null,
                nextPage= "query=propri%C3%A9t%C3%A9&resolve_references=true&field=&type=&theme=&chamber=&formation=&jurisdiction=&location=&publication=&solution=&page=1",
                took= 34,
                maxScore =  1292.1495,
                results =  listOf(
                    SearchResult(
                        score = 666.666,
                        id = "hdjzhsjshsjs",
                        jurisdiction = "cc",
                        chamber = "chamber",
                        numbers = listOf("5667"),
                        number = "6475758",
                        publication = listOf("bulletin"),
                        decisionDate = "2023-06-06",
                        type = "type",
                        solution = "rejet",
                        summary = "",
                        highlights = null,
                        files = null,
                        themes = null
                    )
                ),
                relaxed = false,
                searchQuery = "dmkdmefmez"
            )
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
            decision = Decision(
                id = "decisionId",
                source = "source",
                text = "loremipsum text",
                jurisdiction = "cc",
                chamber = "chamber",
                number = "3456787654",
                numbers = listOf("567876", "567898767"),
                publication = listOf("publi-567876", "publi-567898767"),
                decisionDate = "2023-02-28",
                type = "type",
                solution = "rejet",
                summary = "summary",
                themes = listOf("theme-567876", "theme-567898767"),
                partial = false,
            )
        )

        navigateToDecisionScreen()
        composeTestRule.onNodeWithTag("genericTopBar").assertIsDisplayed()
        composeTestRule.onNodeWithTag("solutionDisplayer").assertIsDisplayed()
        composeTestRule.onNodeWithTag("noZones").assertIsDisplayed()
    }

    @Test
    fun decision_is_not_null_with_zones() {
        state = DecisionState(
            decision = Decision(
                id = "decisionId",
                source = "source",
                zones = mapOf(
                    "introduction" to listOf(
                        ZoneSegment(0, 1495)
                    )
                ),
                text = "loremipsum text",
                jurisdiction = "cc",
                chamber = "chamber",
                number = "3456787654",
                numbers = listOf("567876", "567898767"),
                publication = listOf("publi-567876", "publi-567898767"),
                decisionDate = "2023-02-28",
                type = "type",
                solution = "rejet",
                summary = "summary",
                themes = listOf("theme-567876", "theme-567898767"),
                partial = false,
            )
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