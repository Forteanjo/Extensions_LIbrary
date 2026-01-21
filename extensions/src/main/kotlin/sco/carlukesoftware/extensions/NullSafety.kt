package sco.carlukesoftware.extensions

/**
 * Returns the value of this nullable type if it's not null, or a provided default value if it is.
 * This is a concise alternative to the elvis operator (`?:`) for cases where the default value
 * is computed or retrieved from a function.
 *
 * @param T The type of the value.
 * @param defaultValue A lambda function that provides the default value to return if the original value is null.
 * @return The original value if not null, otherwise the result of the `defaultValue` lambda.
 */
fun <T> T?.orDefault(defaultValue: T): T {
    return this ?: defaultValue
}

/**
 * Returns the encapsulated value if it's not null, otherwise throws the specified exception.
 * This is an alternative to the `!!` operator, allowing for a more descriptive exception
 * to be thrown when a value is unexpectedly null.
 *
 * @param T The type of the encapsulated value.
 * @param exception The exception to be thrown if the value is null.
 * @return The non-null encapsulated value.
 * @throws exception if the value is null.
 */
fun <T> T?.orThrow(exception: () -> Exception): T {
    return this ?: throw exception()
}

/**
 * Executes a given action if this nullable type is `null`.
 * This function allows for chaining operations and executing side effects when a value is null.
 *
 * @param T The type of the value.
 * @param action The lambda function to be executed if the value is null.
 * @return The original value, allowing for further chained calls.
 */
fun <T> T?.ifNull(action: () -> Unit): T? {
    if (this == null) action()
    return this
}

/**
 * Executes a given block of code if the value of this nullable type is not null.
 * This function allows for safe, chained operations on a nullable object, similar to `let`,
 * but it returns the original receiver (`this`) instead of the result of the lambda.
 * This is useful for performing side effects on a non-null value without altering the flow.
 *
 * @param T The type of the value.
 * @param action The block of code to execute, which receives the non-null value as its argument.
 * @return The original nullable object, allowing for further chaining.
 */
fun <T> T?.ifNotNull(action: (T) -> Unit): T? {
    if (this != null) action(this)
    return this
}
