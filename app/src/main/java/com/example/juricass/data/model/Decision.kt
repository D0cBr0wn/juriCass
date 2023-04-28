package com.example.juricass.data.model
import android.util.Log
import com.example.juricass.data.helpers.getFormatter
import com.example.juricass.ui.decisionScreen.ZoneDisplayer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Serializable
data class Decision (
    val id: String,
    val source: String,
    val zones: Map<String, List<ZoneSegment>>? = null,
    val text: String,
    @SerialName(value = "text_highlight")
    val textHighlight: String? = null,
    val nac: String?,
    @SerialName(value = "update_date")
    val updateDate: String,//TODO: handle date properly
    val visa: List<TextLink>,
    val rapprochements: List<Rapprochement>,
    @SerialName(value = "to_be_deleted")
    val toBeDeleted: Boolean = false,
    val jurisdiction: String,
    val chamber: String,
    val number: String,
    val numbers: List<String>,
    val ecli: String?,
    val formation: String? = null,
    val publication: List<String>,
    @SerialName(value = "decision_date")
    val decisionDate: String,//TODO: handle date properly
    val type: String,
    val solution: String,
    @SerialName(value = "solution_alt")
    val solutionAlt: String? = null,
    val summary: String,
    val bulletin: String? = null,
    val files: List<FileLink>,
    val themes: List<String>,
    val portalis: String?,
    val forward: String?,
    val contested: Judgement?,
    val timeline: List<Judgement>?,
    val partial: Boolean,
    val legacy: Unknown?,//TODO: find proper object
) {
    val decisionDateAsObject : OffsetDateTime
        get() {
            return OffsetDateTime.parse(decisionDate)
        }
    val formattedDecisionDate : String
        get() {
            val date = LocalDate.parse(decisionDate)
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            return date.format(formatter)
        }

}