package sco.carlukesoftware.extensions

import java.sql.Timestamp

fun createTimestamp(hours: Int, minutes: Int, seconds: Int): Timestamp =
    Timestamp((hours * 24) + (minutes * 60) + seconds.toLong())


const val MILLISECONDS_IN_SECOND = 1000
const val SECONDS_IN_MINUTE = 60
const val MINUTES_IN_HOUR = 60
const val SECONDS_IN_HOUR = SECONDS_IN_MINUTE * MINUTES_IN_HOUR

fun secondsToPrettyTime(milliseconds: Long): String {
    if (milliseconds < 0) return "Invalid input"
    if (milliseconds == 0L) return "Now"

    // Breakdown our milliseconds
    val seconds = (milliseconds / MILLISECONDS_IN_SECOND).toInt()
    val hours = seconds / SECONDS_IN_HOUR
    val minutes = (seconds % SECONDS_IN_HOUR)/MINUTES_IN_HOUR
    val secondsLeft = seconds % SECONDS_IN_MINUTE
    val millisecondsLeft = milliseconds % MILLISECONDS_IN_SECOND

    var result = ""
    if (hours > 0) {
        result += "$hours h"
    }
    if (minutes > 0) {
        result += " $minutes min"
    }
    if (secondsLeft > 0) {
        result += " $secondsLeft sec"
    }
    return result.trim()
}