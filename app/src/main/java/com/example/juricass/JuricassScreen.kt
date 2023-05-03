package com.example.juricass

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.juricass.ui.bookmarksScreen.BookMarksScreen
import com.example.juricass.ui.decisionScreen.DecisionScreen
import com.example.juricass.ui.decisionScreen.DecisionViewModel
import com.example.juricass.ui.decisionScreen.DecisionViewModelFactory
import com.example.juricass.ui.homeScreen.HomeScreen
import com.example.juricass.ui.homeScreen.HomeViewModel
import com.example.juricass.ui.settingsScreen.SettingsScreen


@Composable
fun JuricassApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = JuriCassRoutes.HOME.name,
        modifier = Modifier
    ) {
        composable(route = JuriCassRoutes.HOME.name) {
           // val homeViewModel = ViewModelProvider(this@MainActivity)[HomeViewModel::class.java]
            //val homeState by homeViewModel.homeState.collectAsState()
            //val homeSearch = homeViewModel.homeSearch()
           // HomeScreen(
             //   homeState,
               // navController,
              //  homeSearch = homeViewModel::homeSearch
           // )
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
            val factory = DecisionViewModelFactory(decisionId ?: "")
            //val decisionViewModel = ViewModelProvider(this@MainActivity, factory = factory)[
               //     DecisionViewModel::class.java]
            //val decisionState by decisionViewModel.decisionState.collectAsState()

            //DecisionScreen(
               // state = decisionState,
            //    navController = navController
           // )
        }
    }
}
