package com.example.juricass.ui.bookmarksScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.juricass.ui.common.GenericTopBar
import com.example.juricass.ui.common.ScreenBase

@Composable
fun BookMarksScreen(navController: NavController) {
    ScreenBase(navController = navController) {
        Text("Bookmarks")
    }
}