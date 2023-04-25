package com.example.juricass.ui.decisionScreen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.juricass.data.model.Decision

@Composable
fun DecisionDisplayer(decision: Decision) {
    Text(text= decision.decisionDate)
}