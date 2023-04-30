/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.juricass.HomeScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.juricass.ui.homeScreen.HomeScreen
import com.example.juricass.ui.homeScreen.HomeViewModel
import androidx.compose.foundation.layout.R
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.juricass.data.model.SearchPage
import com.example.juricass.data.model.SearchQuery
import com.example.juricass.data.model.SearchResult
import com.example.juricass.data.state.HomeState
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun home_search_is_null() {
        var homeState = HomeState()

        composeTestRule.setContent {
            HomeScreen(homeState, TestNavHostController(LocalContext.current), {})//
        }

        composeTestRule.onNodeWithTag("homeLoader").assertDoesNotExist()
        composeTestRule.onNodeWithText("No result found").assertIsDisplayed()
    }

    @Test
    fun home_search_is_not_null_no_results() {
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
                results =  emptyList(),
                relaxed = false,
                searchQuery = "dmkdmefmez"
            )
        )

        composeTestRule.setContent {
            HomeScreen(homeState, TestNavHostController(LocalContext.current), {})//
        }

        composeTestRule.onNodeWithText("No result found").assertIsDisplayed()
    }

    @Test
    fun home_search_is_not_null_results() {
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
            HomeScreen(homeState, TestNavHostController(LocalContext.current), {})//
        }

        composeTestRule.onNodeWithTag("homeLazyColumn").assertIsDisplayed()
    }

   // @Test
    //fun home_search_is_loading() {
     //   var homeState = HomeState(isLoading = true)

      //  composeTestRule.setContent {
       //     HomeScreen(homeState, TestNavHostController(LocalContext.current), {})//
       // }

     //   composeTestRule.onNodeWithTag("homeLoader").assertIsDisplayed()
  //  }


    //test destination
}
