package com.example.juricass.ui.homeScreen

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.juricass.R
import com.example.juricass.data.model.SearchResult
import com.example.juricass.ui.common.SearchResultDisplayer
import com.example.juricass.ui.common.SkeletonLoader
import com.example.juricass.ui.theme.JuriCassTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeScreen(viewModel: HomeViewModel, onSearchClick: () -> Unit) {
    val state by viewModel.homeState.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isLoading)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement  = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        if (state.searchPage === null || state.searchPage!!.results.isEmpty() && !state.isLoading) {
            Text(text = stringResource(id = R.string.no_result_found))
        } else {
            SwipeRefresh(state = swipeRefreshState, onRefresh = viewModel::homeSearch) {
                SkeletonLoader(state.isLoading, error = state.error)
                LazyColumn {
                    itemsIndexed(state.searchPage!!.results) { index, item ->
                        if(!state.isLoading) SearchResultDisplayer(item)
                    }
                }
            }
        }
    }
}



@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreviewDark() {
    val viewModel = HomeViewModel()
    JuriCassTheme() {
        HomeScreen(viewModel = viewModel, onSearchClick = {})
    }
}

@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    val viewModel = HomeViewModel()
    JuriCassTheme() {
        HomeScreen(viewModel = viewModel, onSearchClick = {})
    }
}