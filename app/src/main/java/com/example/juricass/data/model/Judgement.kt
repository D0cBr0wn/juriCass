package com.example.juricass.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Judgement (
    val id: String? = null,
    val date: String,//TODO: handle date properly
    val title: String,
    val jurisdiction: String? = null,
    val chamber: String? = null,
    val solution: String? = null,
    val number: String? = null,
    val url: String? = null,
)