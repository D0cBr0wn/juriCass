package com.example.juricass.ui.decisionScreen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.juricass.ui.common.GenericTopBar
import androidx.navigation.NavController
import com.example.juricass.R
import com.example.juricass.data.state.DecisionState
import com.example.juricass.ui.common.CircleLoader
import com.example.juricass.ui.common.ScreenBase
import kotlinx.serialization.json.JsonNull.content

@Composable
fun DecisionScreen(state: DecisionState, navController: NavController) {
    ScreenBase(navController = navController) {
        CircleLoader(state.isLoading, state.error)
        if (!state.isLoading) {
            DecisionDisplayer(decision = state.decision)
       }
    }
}