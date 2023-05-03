package com.example.juricass.ui.decisionScreen

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.juricass.ui.common.GenericTopBar
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.juricass.R
import com.example.juricass.data.fixtures.DecisionFixture
import com.example.juricass.data.fixtures.SearchPageFixture
import com.example.juricass.data.model.Decision
import com.example.juricass.data.state.DecisionState
import com.example.juricass.data.state.HomeState
import com.example.juricass.ui.common.CircleLoader
import com.example.juricass.ui.common.ScreenBase
import com.example.juricass.ui.homeScreen.HomeScreen
import com.example.juricass.ui.theme.JuriCassTheme
import kotlinx.serialization.json.JsonNull.content

@Composable
fun DecisionScreen(state: DecisionState, navController: NavController) {
    ScreenBase(navController = navController) {
        CircleLoader(state.isLoading, state.error)
        if (!state.isLoading) {
            DecisionDisplayer(decision = state.decision, modifier = Modifier)
       }
    }
}

@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Composable
fun DecisionScreenPreviewDark() {
    val navController = rememberNavController()
    JuriCassTheme() {
        DecisionScreen(DecisionState(decision = DecisionFixture.decision()), navController )
    }
}

@Preview("Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true, showSystemUi = true)
@Composable
fun DecisionScreenPreview() {
    val navController = rememberNavController()
    JuriCassTheme() {
        DecisionScreen(DecisionState(decision = DecisionFixture.decision()), navController )
    }
}