package com.example.juricass.ui.decisionScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.juricass.ui.common.GenericTopBar
import androidx.compose.runtime.getValue

@Composable
fun DecisionScreen(viewModel: DecisionViewmodel, onGoHomeClick:() -> Unit) {
    val state by viewModel.decisionState.collectAsState()
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
                Text(state.decision.toString())
            }
        }
        }
    )
}