@file:OptIn(ExperimentalTime::class)

package sco.carlukesoftware.extensions

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.YearMonth
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

fun YearMonth.Companion.now(
    zone: TimeZone = TimeZone.currentSystemDefault()
): YearMonth {
    val dt = Clock.System.now().toLocalDateTime(zone)
    return YearMonth(dt.year, dt.month)
}

/**
 * Returns the following month.
 */
val YearMonth.nextMonth: YearMonth
    get() = this.plus(1, DateTimeUnit.MONTH)


/**
 * Returns the following month.
 */
val YearMonth.prevMonth: YearMonth
    get() = this.minus(1, DateTimeUnit.MONTH)


/**
 * Combines this year-month with a day-of-month to create a `LocalDate`.
 * This returns a `LocalDate` with the year and month from this object,
 * and the provided day-of-month.
 *
 * @param day The day-of-month to use, from 1 to 31.
 * @return The resulting `LocalDate`.
 * @throws IllegalArgumentException if the day-of-month is invalid for the year and month.
 */
fun YearMonth.atDay(day: Int): LocalDate = LocalDate(this.year, this.month, day)

/**
 * Determines if a given year is a leap year.
 * A year is a leap year if it is divisible by 4, unless it is a century year not divisible by 400.
 */
fun YearMonth.isLeapYear(): Boolean {
    val year = this.year
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
}
