package com.example.juricass.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.juricass.ui.homeScreen.HomeScreen
import com.example.juricass.ui.homeScreen.HomeViewModel

enum class JuriCassRoutes() {
    HOME,
}

@Composable
fun JuriCassApp() {
    val navController = rememberNavController()
    val homeViewModel = HomeViewModel()

    NavHost(navController = navController, startDestination = JuriCassRoutes.HOME.name, modifier = Modifier) {
        composable(route = JuriCassRoutes.HOME.name) {
            homeViewModel.homeSearch()
            HomeScreen(
                homeViewModel,
                onSearchClick = { homeViewModel.homeSearch() }
            )
        }
    }

}