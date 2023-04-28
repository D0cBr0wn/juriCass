package com.example.juricass

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.juricass.data.model.SearchPage
import com.example.juricass.data.state.HomeState
import com.example.juricass.ui.homeScreen.HomeScreen
import com.example.juricass.ui.homeScreen.HomeViewModel

import kotlinx.coroutines.flow.flow
import org.junit.Rule
import org.junit.Test
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.testing.TestNavHostController
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.StateFlow

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()



    //When the `searchPage` in the `HomeState` is null, the "No result found" message should be displayed.
    @Composable
    @Test
    fun homeScreen_showNoResultFound_whenSearchPageIsNull() {
        val viewModel = mockk<HomeViewModel>()
        every { viewModel.homeState } returns flow { emit(HomeState()) } as StateFlow<HomeState>
        every { viewModel.homeSearch() } just Runs

        composeTestRule.setContent {
            HomeScreen(viewModel, TestNavHostController(LocalContext.current))
        }

        composeTestRule.onNodeWithText(stringResource(id = R.string.no_result_found)).assertIsDisplayed()
    }


}
