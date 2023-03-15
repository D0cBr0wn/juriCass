package com.example.juricass.data.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Decision (
    val id: String,
    val zones: Zone,
    val text: String,
    @SerialName(value = "text_highlight")
    val textHighlight: String,
    val nac: String,
    @SerialName(value = "update_date")
    val updateDate: String,//TODO: handle date properly
    val visa: List<TextLink>,
    val rapprochements: List<DecisionLink>,
    @SerialName(value = "to_be_deleted")
    val toBeDeleted: Boolean,
    val jurisdiction: String,
    val chamber: String,
    val number: String,
    val ecli: String,
    val formation: String,
    val publication: String,
    @SerialName(value = "decision_date")
    val decisionDate: String,//TODO: handle date properly
    val type: String,
    val solution: String,
    @SerialName(value = "solution_alt")
    val solutionAlt: String,
    val summary: String,
    val bulletin: String,
    val files: List<FileLink>,
    val themes: List<String>,
)