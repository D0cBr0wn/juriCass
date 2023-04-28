package com.example.juricass.data.state

import com.example.juricass.data.model.Decision
import com.example.juricass.data.model.SearchPage

data class HomeState (
    val isLoading: Boolean = false,
    val healthCheck: String = "unset",
    val searchPage: SearchPage? = null,
    val error: String? = null
)