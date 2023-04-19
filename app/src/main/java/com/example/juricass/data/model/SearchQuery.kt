package com.example.juricass.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class SearchQuery(
    val query: String?= null,
    val field: List<String>?= null,
    val operator: String?= null,
    val type: List<String>?= null,
    val theme: List<String>?= null,
    val chamber: List<String>?= null,
    val formation: List<String>?= null,
    val jurisdiction: List<String>?= null,
    val location: List<String>?= null,
    val publication: List<String>?= null,
    val solution: List<String>?= null,
    @SerialName(value = "date_start")
    val dateStart: String?= null,
    @SerialName(value = "date_end")
    val dateEnd: String?= null,
    val sort: String?= null,
    val order: String?= null,
    @SerialName(value = "page_size")
    val pageSize: Int?= null,
    val page: Int?= null,
    @SerialName(value = "resolve_references")
    val resolveReferences: Boolean? = false
)
