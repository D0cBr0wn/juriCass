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
import com.example.juricass.data.model.SearchPage
import com.example.juricass.data.model.SearchQuery
import com.example.juricass.data.model.SearchResult
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

    @Before
    fun setupAppNavHost() {
        // Create a TestNavHostController
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        // Set the navigator provider to use the ComposeNavigator
        navController.navigatorProvider.addNavigator(ComposeNavigator())
    }




    @Test
    fun decision_is_null() {
        var state = DecisionState()

        composeTestRule.setContent {
            NavHost(navController = navController, startDestination = JuriCassRoutes.HOME.name) {
                composable(route = JuriCassRoutes.HOME.name) {
                    HomeScreen(
                        HomeState(),
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
        // navigate to the DecisionScreen
        navController.navigate(JuriCassRoutes.DECISION.name + "/null")

        composeTestRule.onNodeWithTag("nothingFound").assertIsDisplayed()
    }
//
//    @Test
//    fun home_search_is_not_null_no_results() {
//        var homeState = HomeState(
//            searchPage = SearchPage(
//                page= 0,
//                pageSize= 10,
//                query = SearchQuery(
//                    query= "propriété",
//                    resolveReferences =  true
//                ),
//                total =  10000,
//                previousPage =  null,
//                nextPage= "query=propri%C3%A9t%C3%A9&resolve_references=true&field=&type=&theme=&chamber=&formation=&jurisdiction=&location=&publication=&solution=&page=1",
//                took= 34,
//                maxScore =  1292.1495,
//                results =  emptyList(),
//                relaxed = false,
//                searchQuery = "dmkdmefmez"
//            )
//        )
//
//        composeTestRule.setContent {
//            HomeScreen(homeState, navController, {})//
//        }
//
//        composeTestRule.onNodeWithText("No result found").assertIsDisplayed()
//    }
//
//    @Test
//    fun home_search_is_not_null_results() {
//        var homeState = HomeState(
//            searchPage = SearchPage(
//                page= 0,
//                pageSize= 10,
//                query = SearchQuery(
//                    query= "propriété",
//                    resolveReferences =  true
//                ),
//                total =  10000,
//                previousPage =  null,
//                nextPage= "query=propri%C3%A9t%C3%A9&resolve_references=true&field=&type=&theme=&chamber=&formation=&jurisdiction=&location=&publication=&solution=&page=1",
//                took= 34,
//                maxScore =  1292.1495,
//                results =  listOf(
//                    SearchResult(
//                        score = 666.666,
//                        id = "hdjzhsjshsjs",
//                        jurisdiction = "cc",
//                        chamber = "chamber",
//                        numbers = listOf("5667"),
//                        number = "6475758",
//                        publication = listOf("bulletin"),
//                        decisionDate = "2023-06-06",
//                        type = "type",
//                        solution = "rejet",
//                        summary = "",
//                        highlights = null,
//                        files = null,
//                        themes = null
//                    )
//                ),
//                relaxed = false,
//                searchQuery = "dmkdmefmez"
//            )
//        )
//
//        composeTestRule.setContent {
//            NavHost(navController = navController, startDestination = JuriCassRoutes.HOME.name) {
//                composable(route = JuriCassRoutes.HOME.name) {
//                    HomeScreen(homeState, navController, {})
//                }
//                composable(route = JuriCassRoutes.DECISION.name + "/{decisionId}",
//                    arguments = listOf(navArgument("decisionId") { type = NavType.StringType })
//                ) { navBackStackEntry ->
//                    val decisionId = navBackStackEntry.arguments?.getString("decisionId")
//
//                    DecisionScreen(
//                        state = DecisionState(),
//                        navController = navController
//                    )
//                }
//            }
//        }
//        composeTestRule.onNodeWithTag("homeLazyColumn").assertIsDisplayed()
//        composeTestRule.onNodeWithTag("decisionButton").assertIsDisplayed()
//        composeTestRule.onNodeWithTag("decisionButton").performClick()
//        navController.assertCurrentRouteName(JuriCassRoutes.DECISION.name + "/{decisionId}")//TODO: find a way to work with proper Id
//    }
}