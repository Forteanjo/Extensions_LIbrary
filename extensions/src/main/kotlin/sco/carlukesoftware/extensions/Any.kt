package sco.carlukesoftware.extensions

import android.util.Log
import kotlin.reflect.KClass

/**
 * Extension function to get the [KClass] of any non-null object.
 * This is a convenient alternative to `T::class` when dealing with generic types,
 * or simply as a replacement for `this.javaClass.kotlin`.
 *
 * @return The [KClass] representation of the object's class.
 * @param <T> The non-nullable type of the receiver object.
 */
fun <T: Any> T.getClass(): KClass<T> {
    return javaClass.kotlin
}

/**
 * An extension property to simplify getting the class name for logging purposes.
 * It returns the string representation of the class, which is suitable for use as a Logcat tag.
 *
 * Example: `Log.d(this.TAG, "My log message")`
 */
inline val Any.TAG: String
    get() = this::class.java.toString()

/**
 * Executes the given [block] only if the receiver object is not `null`.
 *
 * This function provides a concise and readable way to perform an action
 * on a nullable object, avoiding an explicit null check.
 *
 * Example:
 * ```
 * val name: String? = "John"
 * name.onNotNull {
 *     println("Name is not null")
 * }
 * ```
 *
 * @param block The block of code to execute if the object is not `null`.
 */
inline fun Any?.onNotNull(block: ()-> Unit) = if (this != null) { block() } else Unit

/**
 * Executes the given [block] only if this object is null.
 *
 * This inline function provides a concise way to perform an action when a nullable object is null,
 * acting as a syntactic sugar for a standard `if (this == null) { ... }` check.
 *
 * Example:
 * ```
 * var user: User? = null
 * user.onIsNull {
 *     println("User is not initialized.")
 * }
 * // Output: User is not initialized.
 *
 * user = User("John")
 * user.onIsNull {
 *     println("This will not be printed.")
 * }
 * // No output
 * ```
 *
 * @param block The lambda function to be executed if the object is null.
 * @see onNotNull
 */
inline fun Any?.onIsNull(block: () -> Unit) = if (this == null) { block() } else Unit

/**
 * A null-safe version of the standard `toString()` function.
 *
 * If the object is `null`, it returns the string "null". Otherwise, it calls the object's
 * regular `toString()` method. This is particularly useful for logging and debugging,
 * preventing `NullPointerException` when concatenating strings.
 *
 * @return The string representation of the object, or "null" if the object is `null`.
 */
fun Any?.toString(): String = this?.toString() ?: "null"

/**
 * A convenient extension property to check if a nullable object is `null`.
 *
 * This property serves as a more readable and fluent alternative to the standard `this == null` check.
 * It enhances code clarity, especially within conditional expressions or when chaining calls.
 *
 * Example:
 * ```
 * val name: String? = null
 * if (name.isNull) {
 *     println("Name is null.")
 * }
 * // Output: Name is null.
 * ```
 *
 * @return `true` if the object is `null`, `false` otherwise.
 * @see isNotNull
 */
val Any?.isNull: Boolean get() = this == null
val Any?.isNotNull: Boolean get() = this != null

fun Any?.printToLog(tag: String = "DEBUG_LOG") {
    Log.d(tag, toString())
}

/**
 * Checks if the object is of the specified generic type `T` or is `null`.
 * This is a convenient shorthand for `this is T?`.
 *
 * @return `true` if the receiver is an instance of `T` or is `null`, `false` otherwise.
 * @param T The type to check against.
 */
inline fun <reified T> Any?.isTypeOfOrNull() = this is T?
