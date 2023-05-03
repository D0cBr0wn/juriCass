package com.example.juricass.data.fixtures

import com.example.juricass.data.model.SearchPage
import com.example.juricass.data.model.SearchQuery
import com.example.juricass.data.model.SearchResult

class SearchPageFixture {
    companion object {
        fun searchPage(results: List<SearchResult> = listOf(SearchResultFixture.searchResult())): SearchPage {
            return SearchPage(
                page = 0,
                pageSize = 10,
                query = SearchQuery(
                    query = "propriété",
                    resolveReferences = true
                ),
                total = 10000,
                previousPage = null,
                nextPage = "query=propri%C3%A9t%C3%A9&resolve_references=true&field=&type=&theme=&chamber=&formation=&jurisdiction=&location=&publication=&solution=&page=1",
                took = 34,
                maxScore = 1292.1495,
                results = results,
                relaxed = false,
                searchQuery = "dmkdmefmez"
            )
        }

        fun searchPageNoResults(): SearchPage {
            return searchPage(results = emptyList())
        }
    }
}