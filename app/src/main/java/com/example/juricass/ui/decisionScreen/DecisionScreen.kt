package com.example.juricass.ui.decisionScreen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.juricass.ui.common.GenericTopBar
import androidx.navigation.NavController
import com.example.juricass.R
import kotlinx.serialization.json.JsonNull.content

@Composable
fun DecisionScreen(viewModel: DecisionViewmodel, navController: NavController) {
    val state by viewModel.decisionState.collectAsState()

    Scaffold(
        topBar = { GenericTopBar(navController) },
        modifier = Modifier,
        content = { padding -> Column(modifier = Modifier.padding(padding)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement  = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                if(state.decision != null) {
                    DecisionDisplayer(decision = state.decision!!)
                } else {
                    Text(text= stringResource(R.string.noDecisionFound))
                }
            }
        }
        }
    )
}