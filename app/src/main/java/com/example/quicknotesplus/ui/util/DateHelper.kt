package com.example.quicknotesplus.ui.util

import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

private const val DATE_FORMAT_PATTERN = "dd MM yyyy, hh::mm a"

fun formatRelativeTime(timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - timestamp

    return when {
        diff < 60_000 -> "Just now"
        diff < 3_600_000 -> "${diff / 60_000} min ago"
        diff < 86_400_000 -> "${diff / 3_600_000} hrs ago"
        else -> formatTimestamp(timestamp)
    }
}

fun formatTimestamp(timestamp: Long): String {
    val sdf = SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.getDefault())
    val date = Date(timestamp)
    return sdf.format(date)
}