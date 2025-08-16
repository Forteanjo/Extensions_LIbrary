package sco.carlukesoftware.extensions

/**
 * Returns [true] if the given range contains the [other] range
 */
operator fun <T : Comparable<T>> ClosedRange<T>.contains(other: ClosedRange<T>): Boolean {
    return this.start in other && this.endInclusive in other
}

/**
 * Returns [true] if the given range overlaps with the [other] range
 */
fun <T : Comparable<T>> ClosedRange<T>.overlaps(other: ClosedRange<T>): Boolean {
    return this.start in other || this.endInclusive in other
}
