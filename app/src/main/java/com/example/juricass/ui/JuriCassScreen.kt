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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.juricass.ui.bookmarksScreen.BookMarksScreen
import com.example.juricass.ui.decisionScreen.DecisionScreen
import com.example.juricass.ui.decisionScreen.DecisionViewmodel
import com.example.juricass.ui.homeScreen.HomeScreen
import com.example.juricass.ui.homeScreen.HomeViewModel
import com.example.juricass.ui.settingsScreen.SettingsScreen

enum class JuriCassRoutes() {
    HOME,
    SETTINGS,
    BOOKMARKS,
    DECISION
}


@Composable
fun JuriCassApp() {
    val navController = rememberNavController()
    val homeViewModel = HomeViewModel()
    val decisionViewModel = DecisionViewmodel()

    NavHost(
        navController = navController,
        startDestination = JuriCassRoutes.HOME.name,
        modifier = Modifier
    ) {
        composable(route = JuriCassRoutes.HOME.name) {
            HomeScreen(
                homeViewModel,
                navController,
            )
        }
        composable(route = JuriCassRoutes.SETTINGS.name) {
            SettingsScreen(
                navController
            )
        }
        composable(route = JuriCassRoutes.BOOKMARKS.name) {
            BookMarksScreen(
                navController
            )
        }
        composable(route = JuriCassRoutes.DECISION.name + "/{decisionId}",
            arguments = listOf(navArgument("decisionId") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val decisionId = navBackStackEntry.arguments?.getString("decisionId")
            DecisionScreen(
                viewModel = DecisionViewmodel(),
                navController = navController,
                decisionId = decisionId
            )
        }
    }
}

