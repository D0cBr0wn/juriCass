package com.example.juricass.data.state

data class MainActivityState(
    val isLoading: Boolean = false,
    val healthCheck: String = "unset",
    val error: String? = null
)