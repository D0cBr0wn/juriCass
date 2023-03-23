package com.example.juricass.data.helpers

import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.*

object DateFormatter {
    fun getFormatter(): DateTimeFormatter {
        val locale = Locale.getDefault()
        return DateTimeFormatterBuilder()
            .appendPattern(when (locale.country) {
                "US", "CA" -> "MM/dd/yyyy"
                else -> "dd/MM/yyyy"
            })
            .toFormatter(locale)
    }
}