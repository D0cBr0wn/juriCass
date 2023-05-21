package com.example.juricass.ui.homeScreen

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.juricass.R
import com.example.juricass.data.fixtures.SearchPageFixture
import com.example.juricass.data.model.Decision
import com.example.juricass.data.model.SearchResult
import com.example.juricass.data.state.HomeState
import com.example.juricass.ui.common.HomeTopBar
import com.example.juricass.ui.common.SearchResultDisplayer
import com.example.juricass.ui.common.SkeletonLoader
import com.example.juricass.ui.theme.JuriCassTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.time.LocalDate


@Composable
fun HomeScreen(
    state: HomeState,
    navController: NavController,
    homeSearch: () -> Unit = {},
    onSearchQueryChanged: (String) -> Unit,
    onStartDateSet: (LocalDate) -> Unit,
    onEndDateSet: (LocalDate) -> Unit,
    onExactSet: (Boolean) -> Unit,
    resetFields: () -> Unit,
    onMenuTriggerClick: () -> Unit,
    onCloseMenu: () -> Unit
) {

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isLoading)

    Scaffold(
        topBar = { HomeTopBar(navController, state, onSearchQueryChanged, onStartDateSet, onEndDateSet, onExactSet, resetFields, homeSearch, onMenuTriggerClick, onCloseMenu) },
        modifier = Modifier,
        content = { padding -> Column(modifier = Modifier.padding(padding)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement  = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {

                SwipeRefresh(state = swipeRefreshState, onRefresh = homeSearch ) {
                    SkeletonLoader(state.isLoading, error = state.error)
                    if (state.searchPage === null || state.searchPage!!.results.isEmpty()) {
                        if(!state.isLoading){
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement  = Arrangement.Top,
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Spacer(modifier = Modifier.height(32.dp))
                                Text(text = stringResource(id = R.string.no_result_found))
                                Spacer(modifier = Modifier.height(32.dp))
                                Button(onClick = homeSearch) {
                                    Text("Refresh")
                                }
                            }
                        }
                   } else {
                        LazyColumn(modifier = Modifier.testTag("homeLazyColumn"))  {
                            itemsIndexed(state.searchPage!!.results) { index, item ->
                              if (!state.isLoading) SearchResultDisplayer(item, navController)
                           }
                        }
                    }
                }
            }
            }
        }
    )
}


//test
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Composable
fun EmptyHomeScreenPreviewDark() {
    val navController = rememberNavController()
    JuriCassTheme() {
        HomeScreen(HomeState(), navController, {} , {}, {}, {}, {}, {}, {}, {})
    }
}

@Preview("Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true, showSystemUi = true)
@Composable
fun EmptyHomeScreenPreview() {
    val navController = rememberNavController()
    JuriCassTheme() {
        HomeScreen(HomeState(), navController, {}, {}, {}, {}, {}, {}, {}, {})
    }
}

@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreviewDark() {
    val navController = rememberNavController()
    JuriCassTheme() {
        HomeScreen(HomeState(searchPage = SearchPageFixture.searchPage()), navController, {} , {}, {}, {}, {}, {}, {}, {})
    }
}

@Preview("Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    JuriCassTheme() {
        HomeScreen(HomeState(searchPage = SearchPageFixture.searchPage()), navController, {} , {}, {}, {}, {}, {}, {}, {})
    }
}