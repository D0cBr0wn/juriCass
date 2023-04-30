package com.example.juricass.HomeScreen

import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.juricass.data.model.SearchResult
import com.example.juricass.ui.JuriCassRoutes
import com.example.juricass.ui.common.SearchResultDisplayer
import com.example.juricass.ui.homeScreen.HomeScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchResultDisplayerTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun everybody_here() {
        val result = SearchResult(
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
        lateinit var navController: TestNavHostController


        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            SearchResultDisplayer(result, navController = navController)
        }

        composeTestRule.onNodeWithTag("surface").assertIsDisplayed()
        composeTestRule.onNodeWithTag("decisionButton").assertIsDisplayed()
        //composeTestRule.onNodeWithTag("decisionButton").performClick()

        //val route = navController.currentDestination?.route
        //Assert.assertEquals(route, JuriCassRoutes.DECISION.name + "/thisisanid")

    }
}