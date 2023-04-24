package com.example.juricass.ui.common

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.juricass.ui.theme.JuriCassTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun TopBar(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    val menuItems = listOf("Option 1", "Option 2", "Option 3")
    TopAppBar(
        title = { Text("JuriCass") },
        navigationIcon = {
            if (navController.previousBackStackEntry != null) {
                //if we have a previous page we display the previous button
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            } else {
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
                                0 -> {}// Handle Option 1,
                                1 -> {}// Handle Option 2,
                                2 -> {}// Handle Option 3,
                            }
                        }) {
                            Text(title)
                        }
                    }
                }
            }

        },
        actions = {}
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Composable
fun TopBarPreview() {
    val navController = rememberNavController()
    JuriCassTheme() {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            TopBar(navController)
        }
    }
}