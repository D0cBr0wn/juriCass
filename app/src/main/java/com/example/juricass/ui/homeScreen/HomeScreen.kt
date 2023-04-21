package com.example.juricass.ui.homeScreen

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
import androidx.compose.ui.unit.dp
import com.example.juricass.R
import com.example.juricass.data.model.SearchResult
import com.example.juricass.ui.common.SkeletonLoader

@Composable
fun HomeScreen(viewModel: HomeViewModel, onSearchClick: () -> Unit) {
    val state by viewModel.homeState.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement  = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Button(onClick = onSearchClick) {
            Text(text = "Reload")
        }

        if (state.searchPage === null || state.searchPage!!.results.isEmpty() && !state.isLoading) {
            Text(text = stringResource(id = R.string.no_result_found))
        } else {
            LazyColumn {
                item {
                    SkeletonLoader(state.isLoading, error = state.error)
                }
                itemsIndexed(state.searchPage!!.results) { index, item ->
                    SearchResultDisplayer(item)
                }
            }
        }
    }
}

@Composable
fun SearchResultDisplayer(result: SearchResult) {
    Column() {
        Row(modifier= Modifier.padding(8.dp)) {
            Text(text = result.decisionDate)
            Text(text = result.jurisdiction)
        }
        Row(modifier= Modifier.padding(8.dp)) {
            for(publi in result.publication) {
                Text(text = publi)
            }
        }
        Row(modifier= Modifier.padding(8.dp)) {
            Text(text = result.solution)
        }
    }

}