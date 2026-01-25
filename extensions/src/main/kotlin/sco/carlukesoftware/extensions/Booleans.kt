package sco.carlukesoftware.extensions

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Executes the given [block] if the Boolean is `true`.
 *
 * This function allows for chaining operations based on the Boolean's value.
 *
 * @param block The function to execute if this Boolean is `true`. The Boolean itself is passed as an argument to the block.
 * @return The original Boolean value.
 */
inline fun Boolean.whenTrue(block: (Boolean) -> Unit): Boolean {
    if (this) block(true)
    return this
}

/**
 * Executes the given [block] if the Boolean value is `false`.
 *
 * This function is useful for chaining operations based on a boolean condition
 * without breaking the flow.
 *
 * Example:
 * ```
 * val fileExists = File("path/to/file").exists()
 * fileExists.whenFalse {
 *     println("File does not exist, creating it now.")
 *     // logic to create the file
 * }
 * ```
 *
 * @param block The block of code to execute. The boolean value (`false`) is passed as its argument.
 * @return The original Boolean value (`this`).
 */
inline fun Boolean.whenFalse(block: (Boolean) -> Unit): Boolean {
    if (!this) block(false)
    return this
}

@OptIn(ExperimentalContracts::class)
fun Boolean?.isTrue(): Boolean {
    contract {
        returns(true) implies (this@isTrue != null)
    }

    return this == true
}

@OptIn(ExperimentalContracts::class)
fun Boolean?.isFalse(): Boolean {
    contract {
        returns(true) implies (this@isFalse != null)
    }

    return this == false
}

val Boolean?.orTrue: Boolean
    get() = this ?: true

val Boolean?.orFalse: Boolean
    get() = this ?: false

fun Boolean.toInt(): Int = if (this) 1 else 0
