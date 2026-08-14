package sco.carlukesoftware.extensions

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.toLocalDateTime
import java.time.ZoneId
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * Formats the [LocalDate] into a string representation of the standard UK date form.
 *
 * @return A string representation of the date and time formatted according to the provided pattern.
 */
fun LocalDate.formatDate(): String {
    return this.format(
        LocalDate.Format {
            day()
            chars("/")
            monthNumber()
            chars("/")
            year()
        }
    )
}

/**
 * Converts the time in milliseconds to a [LocalDate].
 *
 * @param timeMillis The time in milliseconds.
 * @return The [LocalDate] part of this date-time, excluding the time information.
 */
@OptIn(ExperimentalTime::class)
fun toLocalDate(timeMillis: Long): LocalDate {
    val instant = Instant.fromEpochMilliseconds(timeMillis)
    // Use the system default time zone for conversion
    return instant.toLocalDateTime(TimeZone.currentSystemDefault()).date
}

//fun LocalDateTime.formatTo(pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
//    return format(DateTimeFormatter.ofPattern(pattern))
//}

/**
 * Checks whether the date falls on a weekend (Saturday or Sunday).
 *
 * @return `true` if the day of the week is Saturday or Sunday, `false` otherwise.
 */
fun LocalDate.isWeekend(): Boolean =
    dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY

/**
 * Checks if the [LocalDate] falls on a weekday (Monday through Friday).
 *
 * @return `true` if the date is a weekday, `false` if it is a weekend.
 */
fun LocalDate.isWeekday(): Boolean = !isWeekend()

//fun LocalDateTime.toEpochMillis(): Long = atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
//}
//fun Long.toLocalDateTime(): LocalDateTime {
//    return LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault())
//}
