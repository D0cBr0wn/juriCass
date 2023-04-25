package com.example.juricass.data.state

import com.example.juricass.data.model.Decision

data class DecisionState(
    val isLoading: Boolean = false,
    val healthCheck: String = "unset",
    val decision: Decision? = null,
    val error: String? = null
)