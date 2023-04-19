package com.example.juricass.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Judgement (
    val id: String? = null,
    val date: String,//TODO: handle date properly
    val title: String,
    val jurisdiction: String,
    val chamber: String,
    val solution: String,
    val number: String,
    val url: String? = null,
)