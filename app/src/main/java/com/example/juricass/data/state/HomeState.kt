package com.example.juricass.data.state

import com.example.juricass.data.model.Decision
import com.example.juricass.data.model.SearchPage
import java.time.LocalDate

data class HomeState (
    val isLoading: Boolean = false,
    val healthCheck: String = "unset",
    val searchPage: SearchPage? = null,
    val searchQuery: String = "",
    val exact: Boolean = false,
    val startDate: String = "",
    val endDate: String = "",
    val error: String? = null
)