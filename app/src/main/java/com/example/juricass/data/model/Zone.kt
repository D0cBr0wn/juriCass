package com.example.juricass.data.model
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
@Serializable
data class Zones(
    @SerialName("introduction") val introduction: List<Zone>,
    @SerialName("expose") val expose: List<Zone>,
    @SerialName("moyens") val moyens: List<Zone>,
    @SerialName("motivations") val motivations: List<Zone>,
    @SerialName("dispositif") val dispositif: List<Zone>,
    @SerialName("annexes") val annexes: List<Zone>
)

@Serializable
data class Zone(
    @SerialName("start") val start: Int,
    @SerialName("end") val end: Int
)

