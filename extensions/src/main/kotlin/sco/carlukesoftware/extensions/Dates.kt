package sco.carlukesoftware.extensions

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.getDateWithoutTime(): Date {
    try {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return formatter.parse(formatter.format(this)) ?: this
    } catch (e: Exception) {

    }

    return this
}

/**
 * Return date in yyyy-MM-dd format.
 *
 * Note: Month is 1 to 12.
 */
fun Date.getYYYYMMDD(): String {
    return try {
        return DateFormat.format("yyyy-MM-dd", this).toString()
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}

fun Date.toReadableDate(): String {
    val simpleDateFormat = SimpleDateFormat("EEE, dd MMM yyyy", Locale.ENGLISH)
    return try {
        simpleDateFormat.format(this)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}

fun Date.toReadableDateTime(): String {
    val simpleDateFormat = SimpleDateFormat("EEE, dd MMM yyyy, hh:mm a", Locale.ENGLISH)
    return try {
        simpleDateFormat.format(this)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}
