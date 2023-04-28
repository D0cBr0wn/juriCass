package com.example.juricass.data.model
import com.example.juricass.R
import kotlinx.serialization.Serializable

@Serializable
data class ZoneSegment(val start: Int, val end: Int) : Comparable<ZoneSegment> {
    override fun compareTo(other: ZoneSegment): Int {
        return this.start.compareTo(other.start)
    }
}

enum class Zone(val titleResId: Int) {
    INTRODUCTION(R.string.introduction),
    EXPOSE(R.string.expose),
    MOYENS(R.string.moyens),
    MOTIVATIONS(R.string.motivations),
    DISPOSITIF(R.string.dispositif),
    ANNEXES(R.string.annexes),
}