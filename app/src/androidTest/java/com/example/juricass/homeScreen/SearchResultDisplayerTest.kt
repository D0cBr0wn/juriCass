package com.example.juricass.homeScreen

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.juricass.JuriCassRoutes
import com.example.juricass.MainActivity
import com.example.juricass.data.model.SearchResult
import com.example.juricass.ui.common.SearchResultDisplayer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify


@RunWith(AndroidJUnit4::class)
class SearchResultDisplayerTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    lateinit var navController: TestNavHostController
    private val result = SearchResult(
        score = 666.666,
        id = "thisisanid",
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

    @Before
    fun setupAppNavHost() {
        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            //MainNavigation(navController = navController)
            SearchResultDisplayer(result, navController = navController)
        }
    }

    @Test
    fun is_everybody_here() {
        composeTestRule.onNodeWithTag("surface").assertIsDisplayed()
        composeTestRule.onNodeWithTag("decisionButton").assertIsDisplayed()
        //composeTestRule.onNodeWithTag("decisionButton").performClick()

        //val route = navController.currentDestination?.route
        //Assert.assertEquals(route, JuriCassRoutes.DECISION.name + "/thisisanid")
    }

}