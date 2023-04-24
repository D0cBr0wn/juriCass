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
import com.example.juricass.ui.bookmarksScreen.BookMarksScreen
import com.example.juricass.ui.homeScreen.HomeScreen
import com.example.juricass.ui.homeScreen.HomeViewModel
import com.example.juricass.ui.settingsScreen.SettingsScreen

enum class JuriCassRoutes() {
    HOME,
    SETTINGS,
    BOOKMARKS,
}


@Composable
fun JuriCassApp() {
    val navController = rememberNavController()
    val homeViewModel = HomeViewModel()
    homeViewModel.homeSearch()//TODO: check if there is another way to call the pethod at page opening

    NavHost(
        navController = navController,
        startDestination = JuriCassRoutes.HOME.name,
        modifier = Modifier
    ) {
        composable(route = JuriCassRoutes.HOME.name) {

            HomeScreen(
                homeViewModel,
                onSettingsClick = { navController.navigate(JuriCassRoutes.SETTINGS.name) },
                onBookmarksClick = { navController.navigate(JuriCassRoutes.BOOKMARKS.name) }
            )
        }
        composable(route = JuriCassRoutes.SETTINGS.name) {
            SettingsScreen(
                onGoHomeClick = { navController.navigate(JuriCassRoutes.HOME.name) }
            )
        }
        composable(route = JuriCassRoutes.BOOKMARKS.name) {
            BookMarksScreen(
                onGoHomeClick = { navController.navigate(JuriCassRoutes.HOME.name) }
            )
        }
    }
}

