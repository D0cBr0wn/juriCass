package com.example.juricass.data.model
import kotlinx.serialization.Serializable

@Serializable
data class Zone (val key: String, val value: List<ZoneSegment>)