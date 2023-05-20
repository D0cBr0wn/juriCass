package com.example.juricass

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.juricass.ui.theme.JuriCassTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
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
import java.time.LocalDate

enum class JuriCassRoutes() {
    HOME,
    SETTINGS,
    BOOKMARKS,
    DECISION
}
class MainActivity : ComponentActivity() {
    private val mainViewModel = MainActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            JuriCassTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = JuriCassRoutes.HOME.name,
                        modifier = Modifier
                    ) {
                        composable(route = JuriCassRoutes.HOME.name) {
                            val homeViewModel = ViewModelProvider(this@MainActivity)[HomeViewModel::class.java]
                            val homeState by homeViewModel.homeState.collectAsState()
                            HomeScreen(
                                homeState,
                                navController,
                                homeSearch = homeViewModel::homeSearch,
                                onSearchQueryChanged = { searchQuery:String -> homeViewModel.setSearchQuery(searchQuery) },
                                onStartDateSet = { startDate: LocalDate -> homeViewModel.setStartDAte(startDate) },
                                onEndDateSet = { endDate:LocalDate -> homeViewModel.setEndDate(endDate) },
                                onExactSet = { exact:Boolean -> homeViewModel.setExact(exact) }
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
                            val factory = DecisionViewModelFactory(decisionId ?: "")
                            val decisionViewModel = ViewModelProvider(this@MainActivity, factory = factory)[DecisionViewModel::class.java]
                            val decisionState by decisionViewModel.decisionState.collectAsState()

                            DecisionScreen(
                                state = decisionState,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    //val mainViewModel = MainActivityViewModel()
    JuriCassTheme {

    }
}