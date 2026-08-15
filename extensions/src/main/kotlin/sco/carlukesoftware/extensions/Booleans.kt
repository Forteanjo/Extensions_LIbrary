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

/**
 * Checks if the nullable Boolean is `true`.
 *
 * This function provides a safe way to check for `true` on a `Boolean?` value.
 * Unlike a direct `== true` comparison, this function uses contracts to enable
 * smart-casting of the receiver to a non-nullable `Boolean` within the scope
 * where this function returns `true`.
 *
 * Example:
 * ```
 * fun process(data: Boolean?) {
 *     if (data.isTrue()) {
 *         // 'data' is smart-cast to a non-nullable Boolean here
 *         println("Data is definitely true.")
 *     } else {
 *         // 'data' could be false or null
 *         println("Data is either false or null.")
 *     }
 * }
 * ```
 *
 * @return `true` if the Boolean is not null and has a value of `true`, `false` otherwise.
 */
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

/**
 * Returns the value of this [Boolean] if it is not null, otherwise returns `true`.
 *
 * This is a convenience property for `this ?: true`.
 *
 * @return The Boolean value, or `true` if `null`.
 */
val Boolean?.orTrue: Boolean
    get() = this ?: true

/**
 * Returns the value of this Boolean if it is not null, otherwise returns `false`.
 *
 * This is a convenience property for `this ?: false`.
 *
 * @return The Boolean value or `false` if null.
 */
val Boolean?.orFalse: Boolean
    get() = this ?: false

/**
 * Converts this [Boolean] to its integer representation.
 *
 * @return `1` if this is `true`, or `0` if this is `false`.
 */
fun Boolean.toInt(): Int = if (this) 1 else 0
