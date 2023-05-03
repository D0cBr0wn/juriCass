package com.example.juricass.data.fixtures

import com.example.juricass.data.model.SearchResult

class SearchResultFixture {
    companion object {
        fun searchResult(): SearchResult {
            return SearchResult(
                score = 666.666,
                id = "hdjzhsjshsjs",
                jurisdiction = "cc",
                chamber = "chamber",
                numbers = listOf("5667"),
                number = "6475758",
                publication = listOf("bulletin"),
                decisionDate = "2023-06-06",
                type = "type",
                solution = "rejet",
                summary = "",
                highlights = null,
                files = null,
                themes = listOf("theme1", "theme2", "theme3")
            )
        }
    }
}