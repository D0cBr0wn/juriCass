package com.example.juricass.data.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TextLink(
    val id: String? = null,
    @SerialName(value = "URL")
    val url: String? = null,
    val title: String? = null
)