package com.example.juricass.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchPage (
    val page: Int,
    @SerialName(value = "page_size")
    val pageSize: Int,
    val query: SearchQuery,
    val results: List<SearchResult>,
    val total: Int,
    @SerialName(value = "previous_page")
    val previousPage: String?,
    @SerialName(value = "next_page")
    val nextPage: String?,
    val took: Int?,
    @SerialName(value = "max_score")
    val maxScore: Double,
    val relaxed: Boolean,
    val searchQuery: String
)
