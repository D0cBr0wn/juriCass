package com.example.juricass.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Serializable
data class SearchResult(
    val score: Double,
    val highlights: Map<String, List<String>>?,
    val id: String,
    val jurisdiction: String,
    val chamber: String,
    val number: String,
    val numbers: List<String>,
    val ecli: String?= null,
    val formation: String?= null,
    val publication: List<String>,
    @SerialName(value = "decision_date")
    val decisionDate: String,
    val type: String,
    val solution: String,
    @SerialName(value = "solution_alt")
    val solutionAlt: String?= null,
    val summary: String?,
    val bulletin: String?= null,
    val files: List<FileLink>?,
    val themes: List<String>?
) {
    val formattedDecisionDate : String
        get() {
            val date = LocalDate.parse(decisionDate)
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            return date.format(formatter)
        }
}