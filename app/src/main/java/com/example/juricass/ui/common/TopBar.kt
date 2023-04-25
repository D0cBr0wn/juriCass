package com.example.juricass.ui.common

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.juricass.ui.JuriCassRoutes
import com.example.juricass.ui.theme.JuriCassTheme


@Composable
fun HomeTopBar(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    val menuItems = listOf("Bookmarks", "Settings")

    TopAppBar(
        title = { Text("JuriCass") },
        navigationIcon = {
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Filled.Menu, contentDescription = null)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                menuItems.forEachIndexed { index, title ->
                    DropdownMenuItem(onClick = {
                        // Handle menu item click
                        expanded = false
                        when (index) {
                            0 ->  navController.navigate(JuriCassRoutes.BOOKMARKS.name) // Handle Option 1,
                            1 ->  navController.navigate(JuriCassRoutes.SETTINGS.name) // Handle Option 2,
                        }
                    }) {
                        Text(title)
                    }
                }
            }
        },
        actions = {}
    )
}
@Composable
fun GenericTopBar(navController: NavController) {
    TopAppBar(
        title = { Text("JuriCass") },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(JuriCassRoutes.HOME.name) }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {}
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Composable
fun HomeBarPreview() {
    val navController = rememberNavController()
    JuriCassTheme() {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            HomeTopBar(navController)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Composable
fun GenericBarPreview() {
    val navController = rememberNavController()
    JuriCassTheme() {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            GenericTopBar(navController)
        }
    }
}