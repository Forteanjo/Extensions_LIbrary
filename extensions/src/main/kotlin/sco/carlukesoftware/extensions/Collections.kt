package sco.carlukesoftware.extensions

import kotlin.math.roundToInt

/**
 * Returns the first n percent of items out of the given Collection.  The precentage gets rounded
 * to the next integer regardless of the size of the returned [List]
 */
fun <T> Collection<T>.takePercentage(percentage: Double): List<T> {
    require(percentage in 0.0.. 1.0) { "A floating point percentage is required" }
    return this.take((this.size * percentage).roundToInt())
}

/**
 * Merges the given [Collection] of type-equivalent [Maps] together, while grouping the
 * values of each key together and finally applying the given [lambds] to each value
 * to merge the list of values of each key into a single value such that the returned map
 * has the same type-signature again.
 */
fun <K, V> Collection<Map<K, V>>.merge(valueMergeFunction: (List<V>) -> V): Map<K, V> {
    return this
        .flatMap { it.entries }
        .groupBy({ it.key }, {it.value })
        .mapValues { valueMergeFunction(it.value) }
}

/**
 * Returns how many percent of the items of the given [Collection] satisfy the [predicate].
 * @return A percentage represented as a floating-point number between 0 and 1
 */
 fun <T> Collection<T>.percentage(predicate: (T) -> Boolean): Float {
     return this.count { predicate(it) } / this.count().toFloat()
 }
