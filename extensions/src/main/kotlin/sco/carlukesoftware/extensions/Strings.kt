package sco.carlukesoftware.extensions

import android.util.Patterns
import java.util.regex.Pattern

fun String?.orEquals(value: String) = this ?: value

fun String.isEmailValid(): Boolean {
    val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()

    //return !matches(Regex("^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+([a-zA-Z])+"))
    //return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

/**
 * Checks if the string contains any of the specified symbols.
 * @param symbols A list of strings to search for within this string.
 * @return `true` if this string contains at least one of the elements from the [symbols] list, `false` otherwise.
 */
fun String.containsAnySymbol(symbols: List<String>): Boolean {
    return this.findAnyOf(symbols, 0, false) != null
}

/**
 * Converts the string to title case.
 *
 * This function capitalizes the first letter of the string and the first letter of every word
 * after a space. It first trims any leading or trailing whitespace and converts the entire
 * string to lowercase before applying the title case logic.
 *
 * Examples:
 * - "hello world" becomes "Hello World"
 * - "  first name  " becomes "First Name"
 * - "singleword" becomes "Singleword"
 *
 * @return The title-cased version of the string. Returns the original string if it is blank after trimming.
 */
fun String.titleCase(): String {
    val trimmed = trim()
    if(trimmed.isBlank()) return this

    return trimmed.lowercase().mapIndexed { index, char ->
        if (index == 0)
            return@mapIndexed char.uppercase()

        if (this[index - 1].toString().isBlank()) {
            return@mapIndexed char.uppercase()
        }

        char.toString()
    }.reduce { accumulate , string ->
        accumulate + string
    }
}


/**
 * Returns `true` if the specified string consists entirely of
 * US ASCII characters.
 */
val String.isAscii: Boolean
    get() {
        for (index in indices) {
            if (!this[index].isASCII) return false
        }

        return true
    }

/**
 * Returns `true` if the specified string consists entirely of
 * whitespace characters.
 */
val String.isWhitespace: Boolean
    get() {
        for (index in indices) {
            if (!this[index].isWhitespace) return false
        }

        return true
    }

val String.containsLatinLetter: Boolean
    get() = matches(Regex(".*[A-Za-z].*"))

val String.containsDigit: Boolean
    get() = matches(Regex(".*[0-9].*"))

val String.isDigitOnly: Boolean
    get() = matches(Regex("^\\d*\$"))

val String.isAlphabeticOnly: Boolean
    get() = matches(Regex("^[a-zA-Z]*\$"))

val String.isAlphanumeric: Boolean
    get() = matches(Regex("[A-Za-z0-9]*"))

val String.hasLettersAndDigits: Boolean
    get() = containsLatinLetter && containsDigit

val String.isIntegerNumber: Boolean
    get() = toIntOrNull() != null

val String.toDecimalNumber: Boolean
    get() = toDoubleOrNull() != null

fun String.hidePassword() = "*".repeat(this.length)

val String.removeAllWhitespaces: String
    get() = this.replace("\\s+".toRegex(), "")

val String.removeDuplicateWhitespaces: String
    get() = this.replace("\\s+".toRegex(), " ")

operator fun String.times(n: Int): String {
    val sb = StringBuilder()
    repeat(n) {
        sb.append(this)
    }

    return sb.toString()
}

val String.wordCount: Int
    get() = this.split("\\s+".toRegex()).size

operator fun String.get(range: IntRange) = substring(range.first, range.last + 1)

fun CharSequence?.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
