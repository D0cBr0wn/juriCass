package com.example.juricass.data.helpers

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.*


fun getFormatter(): DateTimeFormatter {
    val locale = Locale.getDefault()
    return DateTimeFormatterBuilder()
        .appendPattern(when (locale.country) {
            "US", "CA" -> "MM/dd/yyyy"
            else -> "dd/MM/yyyy"
        })
        .toFormatter(locale)
}

fun convertDatesForQuery(localDate: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return localDate.format(formatter)
}

fun convertDatesForDisplay(localDate: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    return localDate.format(formatter)
}

fun convertQueryDateForDisplay(dateString: String): String {
    val sourceFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val targetFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    val date = LocalDate.parse(dateString, sourceFormat)
    return date.format(targetFormat)
}

