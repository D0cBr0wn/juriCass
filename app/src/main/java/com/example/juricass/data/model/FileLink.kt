package com.example.juricass.data.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FileLink (
    val id: String,
    @SerialName(value = "URL")
    val url: String,
    val type: String,
    val label: String
)