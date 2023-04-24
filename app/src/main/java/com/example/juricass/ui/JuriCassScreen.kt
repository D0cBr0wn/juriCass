package com.example.juricass.ui

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.juricass.ui.common.TopBar
import com.example.juricass.ui.homeScreen.HomeScreen
import com.example.juricass.ui.homeScreen.HomeViewModel

enum class JuriCassRoutes() {
    HOME,
}

@Composable
fun JuriCassApp() {
    val navController = rememberNavController()
    val homeViewModel = HomeViewModel()

    Scaffold(
        topBar = { TopBar(navController) },
        modifier = Modifier,
        content = { padding -> Column(modifier = Modifier.padding(padding)) {
            Box(modifier = Modifier.padding(2.dp)) {


                NavHost(
                    navController = navController,
                    startDestination = JuriCassRoutes.HOME.name,
                    modifier = Modifier
                ) {
                    composable(route = JuriCassRoutes.HOME.name) {
                        homeViewModel.homeSearch()
                        HomeScreen(
                            homeViewModel,
                            onSearchClick = { homeViewModel.homeSearch() }
                        )
                    }
                }
            }
        }
     })
}