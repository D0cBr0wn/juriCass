package com.example.juricass.ui.settingsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.juricass.R
import com.example.juricass.ui.common.GenericTopBar
import com.example.juricass.ui.common.HomeTopBar
import com.example.juricass.ui.common.SearchResultDisplayer
import com.example.juricass.ui.common.SkeletonLoader
import com.google.accompanist.swiperefresh.SwipeRefresh

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = { GenericTopBar(navController) },
        modifier = Modifier,
        content = { padding -> Column(modifier = Modifier.padding(padding)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement  = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Text("Settings")
                }
            }
        }
    )
}