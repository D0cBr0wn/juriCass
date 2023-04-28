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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.juricass.R
import com.example.juricass.data.model.Decision
import com.example.juricass.data.model.SearchResult
import com.example.juricass.ui.common.HomeTopBar
import com.example.juricass.ui.common.SearchResultDisplayer
import com.example.juricass.ui.common.SkeletonLoader
import com.example.juricass.ui.theme.JuriCassTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {

    val state by viewModel.homeState.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isLoading)
    Scaffold(
        topBar = { HomeTopBar(navController) },
        modifier = Modifier,
        content = { padding -> Column(modifier = Modifier.padding(padding)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement  = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                SwipeRefresh(state = swipeRefreshState, onRefresh = viewModel::homeSearch) {
                    if (state.searchPage === null || state.searchPage!!.results.isEmpty() && !state.isLoading) {
                        Text(text = stringResource(id = R.string.no_result_found))
                    } else {

                        SkeletonLoader(state.isLoading, error = state.error)
                        LazyColumn {
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



@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreviewDark() {
    val viewModel = HomeViewModel()
    val navController = rememberNavController()
    JuriCassTheme() {
        HomeScreen(viewModel = viewModel, navController )
    }
}

@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    val viewModel = HomeViewModel()
    val navController = rememberNavController()
    JuriCassTheme() {
        HomeScreen(viewModel = viewModel, navController)
    }
}