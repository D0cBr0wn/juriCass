package com.example.juricass.data.state

import com.example.juricass.data.model.Decision
import com.example.juricass.data.model.SearchPage
import java.time.LocalDate

data class HomeState (
    val isLoading: Boolean = false,
    val menuExpanded: Boolean = false,
    val healthCheck: String = "unset",
    val searchPage: SearchPage? = null,
    val searchQuery: String = "Propriété",
    val exact: String? = null,
    val startDate: String? = null,
    val endDate: String? = null,
    val error: String? = null
)