package com.example.juricass.ui.bookmarksScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.juricass.ui.common.GenericTopBar

@Composable
fun BookMarksScreen(onGoHomeClick:() -> Unit) {
    Scaffold(
        topBar = { GenericTopBar(onGoHomeClick = onGoHomeClick) },
        modifier = Modifier,
        content = { padding -> Column(modifier = Modifier.padding(padding)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement  = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Text("Bookmarks")
            }
        }
        }
    )
}