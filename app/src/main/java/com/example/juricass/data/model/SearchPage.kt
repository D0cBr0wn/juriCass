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

val fixture = SearchPage(
        page= 0,
        pageSize= 10,
        query = SearchQuery(
            query= "propriété",
            resolveReferences =  true
        ),
        total =  10000,
        previousPage =  null,
        nextPage= "query=propri%C3%A9t%C3%A9&resolve_references=true&field=&type=&theme=&chamber=&formation=&jurisdiction=&location=&publication=&solution=&page=1",
        took= 34,
        maxScore =  1292.1495,
        results =  emptyList(),
        relaxed = false,
        searchQuery = "dmkdmefmez"
    )