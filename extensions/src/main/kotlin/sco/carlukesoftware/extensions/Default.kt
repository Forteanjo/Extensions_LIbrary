package sco.carlukesoftware.extensions

/**
 * Returns this value if it's not null, or the default value otherwise.
 *
 * This is a convenience function that provides a more readable way to express the elvis operator (`?:`).
 *
 * For example, instead of writing `val name = user?.name ?: "Guest"`, you can write
 * `val name = user?.name.or("Guest")`.
 *
 * @param default The value to return if this is null.
 * @return This value if it's not null, or the default value otherwise.
 */
fun <T> T?.or(default: T): T = this ?: default

/**
 * Maps this nullable value to a new value using the given transformation function, or returns the default value if this is null or the transformation returns null.
 *
 * This function is similar to `map`, but it also allows you to specify a default value to return if the original value is null or if the transformation function returns null.
 *
 * For example, if you have a nullable `User` object and you want to get the user's name, or "Guest" if the user is null or their name is null, you can write:
 *
 * ```kotlin
 * val userName = user.mapOr("Guest") { it.name }
 * ```
 *
 * @param default The value to return if this is null or the transformation returns null.
 * @param transform The function to apply to this value if it's not null.
 * @return The result of applying the transformation function to this value, or the default value if this is null or the transformation returns null.
 */
inline fun <T, R> T?.mapOr(default: R, transform: (T) -> R?): R = this?.let(transform)
    .or(default)


/**
 * Takes two nullable values along with a lambda function that operates on them and returns a nullable result.
 */
inline fun <T1, T2, R> ifLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? =
    if (p1 != null && p2 != null) block(p1, p2) else null
