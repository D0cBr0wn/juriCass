package com.example.juricass.data.state

import com.example.juricass.data.model.Decision
import com.example.juricass.data.model.SearchPage
import com.example.juricass.data.model.SearchResult

data class MainActivityState(
    val isLoading: Boolean = false,
    val error: String? = null
)