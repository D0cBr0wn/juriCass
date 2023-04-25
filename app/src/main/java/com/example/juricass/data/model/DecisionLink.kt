package com.example.juricass.data.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DecisionLink(
    val id: String,
    @SerialName(value = "URL")
    val url: String,
    val description: String,
    val theme: List<String>,
    val number: String,
)