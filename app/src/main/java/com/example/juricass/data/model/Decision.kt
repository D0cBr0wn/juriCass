package com.example.juricass.data.model
import com.example.juricass.data.helpers.getFormatter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime
import java.util.*

@Serializable
data class Decision (
    val id: String,
    val source: String,
    val zones: Zones,
    val text: String,
    @SerialName(value = "text_highlight")
    val textHighlight: String? = null,
    val nac: String?,
    @SerialName(value = "update_date")
    val updateDate: String,//TODO: handle date properly
    @SerialName(value = "update_datetime")
    val updateDateTime: String,//TODO: handle date properly
    val visa: List<TextLink>,
    val rapprochements: List<DecisionLink>,
    @SerialName(value = "to_be_deleted")
    val toBeDeleted: Boolean = false,
    val jurisdiction: String,
    val chamber: String,
    val number: String,
    val numbers: List<String>,
    val ecli: String,
    val formation: String,
    val publication: List<String>,
    @SerialName(value = "decision_date")
    val decisionDate: String,//TODO: handle date properly
    @SerialName(value = "decision_datetime")
    val decisionDateTime: String,//TODO: handle date properly
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
    val timeline: List<Judgement>,
    val partial: Boolean,
    val legacy: Unknown?//TODO: find proper object
) {
    val decisionDateAsObject : OffsetDateTime
        get() {
            return OffsetDateTime.parse(decisionDate)
        }
    val formattedDecisionDate : String
        get() {
            return decisionDateAsObject.format(getFormatter())
        }
}