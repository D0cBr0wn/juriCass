package com.example.juricass.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ScreenBase(navController: NavController, title: String? = null, content: @Composable () -> Unit) {
    Scaffold(
        topBar = { GenericTopBar(navController) },
        modifier = Modifier,
        content = { padding -> Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement  = Arrangement.Top,
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
                .fillMaxHeight()
            ) {
                content()
            }
        }
    )
}