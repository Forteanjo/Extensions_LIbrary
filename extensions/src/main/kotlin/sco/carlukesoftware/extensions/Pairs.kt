package sco.carlukesoftware.extensions

/**
* Swaps the order of the elements of the given [Pair] in a type-safe manner.
* Example:
* Pair("David", 32).swap() -> Pair(32, "David")
*/
fun <A, B> Pair<A, B>.swap(): Pair<B, A> {
    return this.second to this.first
}

val triple : Triple<String, Int, String> = "Kotlin" to 2011 to "JetBrains"

/**
 * Creates a [Triple] from a [Pair] and a third value.
 * This allows for a chained call like `"A" to "B" to "C"`.
 * @param third The third value.
 * @return A new [Triple] containing the elements from the original [Pair] and the new third value.
 */
infix fun <A, B, C> Pair<A, B>.to(third:C): Triple<A, B, C> {
    return Triple(first, second, third)
}
