package sco.carlukesoftware.extensions

import android.R.attr.digits
import java.text.MessageFormat
import java.util.Locale
import kotlin.math.abs
import kotlin.math.pow


// null == 0
fun Number?.orZero(): Number = or(0)
fun Int?.orZero(): Int = or(0)
fun Float?.orZero(): Float = or(0f)
fun Double?.orZero(): Double = or(0.0)

/**
 * Returns this number if it's within the specified range (inclusive), or the default value otherwise.
 *
 * This function checks if the current number falls between the `min` and `max` values.
 * If it does, the current number is returned. Otherwise, the `default` number is returned.
 *
 * Example:
 * ```
 * val age = 25
 * val validAge = age.inRangeOr(18, 65, 18) // validAge will be 25
 *
 * val temperature = 10
 * val adjustedTemp = temperature.inRangeOr(15, 30, 20) // adjustedTemp will be 20
 * ```
 *
 * @param min The minimum value of the range (inclusive).
 * @param max The maximum value of the range (inclusive).
 * @param default The value to return if this number is not within the specified range.
 * @return This number if it's in the range [min, max], or `default` otherwise.
 */
fun Int.inRangeOr(min: Int, max: Int, default: Int): Int = if (this in min..max) this else default
fun Number.InRangeOr(min: Number, max: Number, default: Number): Number = if (this.toFloat() in min.toFloat()..max.toFloat()) this else default

// Percentages
fun Number.asPercentage(ofValue: Number) =  this.toFloat() / ofValue.toFloat()
fun Int.asPercentage(ofValue: Int) = (this.toFloat()/ ofValue.toFloat()).toInt()
fun Long.asPercentage(ofValue: Long) = (this.toFloat()/ ofValue.toFloat()).toLong()
fun Double.asPercentage(ofValue: Double) = this.toDouble() / ofValue.toDouble()

fun Int.percentageOf(total: Int): Float = 0f.takeIf{ total == 0 }.or( (toFloat() / total) * 100)

/**
 * Calculate size into human-readable size
 */
fun Long.toNumInUnits(): String {
    var bytes = this
    var u = 0

    while (bytes > 1024 * 1024) {
        u++
        bytes = bytes shr 10
    }

    if (bytes > 1024) {
        u++
    }

    return String.format(Locale.ROOT,
        "%.1f %cB", bytes / 1024f, " kMGTPE"[u])
}

fun Number.formatDecimal(digits: Int = 2): String = "%.${digits}f".format(toDouble())


operator fun Int.get(digit: Int) =
    div(10.0.pow(digit.toDouble()))
        .rem(10.0)
        .toInt()

fun Int.lastDigit(): Int =
    abs(this) % 10
//        .toString()
//        .last()
//        .toString()
//        .toInt()


fun Int.toWords(language: String = "en", country: String = "US"): String {
    val formatter = MessageFormat(
        "{0,spellout,currency}",
        Locale(language, country)
    )

    return formatter.format(arrayOf(this))
}

fun Long.milliSecondsToTimeString(): String {
    var result = ""
    val hours = (this / (1000 * 60 * 60))
    val minutes = (this % (1000 * 60 * 60)) / (1000 * 60)
    val seconds = (this % (1000 * 60 * 60) % (1000 * 60) / 1000)

    if(hours >0){
        result = "$hours:"
    }
    val secondsString = if(seconds < 10) "0$seconds" else "$seconds"

    result = "$result$minutes:$secondsString"
    return result
}

fun Int.toBoolean(): Boolean = this != 0

fun Number.swap(newNumber: Number): Pair<Number, Number> = Pair(newNumber, this)
