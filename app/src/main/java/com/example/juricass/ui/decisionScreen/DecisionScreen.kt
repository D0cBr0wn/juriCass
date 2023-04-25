package com.example.juricass.ui.decisionScreen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.juricass.ui.common.GenericTopBar
import androidx.navigation.NavController
import kotlinx.serialization.json.JsonNull.content

@Composable
fun DecisionScreen(viewModel: DecisionViewmodel, navController: NavController, decisionId: String?) {
    val state by viewModel.decisionState.collectAsState()
    val currentDecisionId = remember { mutableStateOf("") }

    if (decisionId != currentDecisionId.value && decisionId !== null) {
        currentDecisionId.value = decisionId
        viewModel.getDecision(decisionId)
    }

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
                Text(state.decision.toString())
                Log.e("compo", state.decision.toString())
            }
        }
        }
    )
}