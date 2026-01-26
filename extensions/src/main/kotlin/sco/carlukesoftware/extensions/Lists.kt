package sco.carlukesoftware.extensions

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

val <T> List<T>.lastIndex: Int
    get() = size - 1

fun <T> List<T>?.orEmpty(): List<T> = this ?: emptyList()
fun <K, V> Map<K, V>?.orEmpty(): Map<K, V> = this ?: emptyMap()
fun <T> Set<T>?.orEmpty(): Set<T> = this ?: emptySet()
fun <T> Collection<T>?.orEmpty(): Collection<T> = this ?: emptyList()

/**
 * Executes the given [block] if this nullable list is not null and not empty.
 *
 * @param block The block of code to execute with this non-empty list as its argument.
 */
inline fun <T> List<T>?.whenNotEmpty(block: (List<T>) -> Unit) {
    if (!this.isNullOrEmpty()) this?.let { block(it) }
}

/**
 * Returns the element at the specified [index] in the list, or the [default] value if the list is `null`
 * or the index is out of bounds.
 *
 * @param index The index of the element to retrieve.
 * @param default The value to return if the list is `null` or the index is invalid.
 * @return The element at the given index or the [default] value.
 */
fun <T> List<T>?.or(index: Int, default: T): T =
    if (this != null && index in indices) this[index] else default

/**
 * Returns a list containing the results of applying the given [transform] function
 * to each element in the original list if the list is not null or empty.
 * If the list is null or empty, it returns an empty list.
 *
 * @param transform The function to apply to each element.
 * @return A new list with the transformed elements, or an empty list if the original was null or empty.
 * @param T The type of elements in the original list.
 * @param R The type of elements in the resulting list.
 */
inline fun <T, R> List<T>?.mapIfNotEmpty(transform: (T) -> R): List<R> =
    if (isNullOrEmpty()) emptyList() else this!!.map(transform)

/**
 * Checks if a list is `null` or empty.
 * @return `true` if the list is `null` or has no elements, `false` otherwise.
 */
fun <T> List<T>?.isNullOrEmpty(): Boolean {
    return this == null || isEmpty()
}

/**
 * Checks if a list is not null and not empty.
 *
 * @return `true` if the list is not null and contains at least one element, `false` otherwise.
 */
fun <T> List<T>?.isNotNullOrEmpty(): Boolean {
    return !isNullOrEmpty()
}

/**
 * Returns the second element.
 * @throws NoSuchElementException if the list has less than 2 elements.
 */
fun <T> List<T>.second(): T {
    if (size < 2) throw NoSuchElementException("List has less than 2 elements")
    return this[1]
}

fun <T> List<T>.secondOrNull(): T? {
    return if (size >= 2) this[1] else null
}
/**
 * Returns this list if it's not empty, or `null` if it is empty.
 * This is a shorthand for `ifEmpty { null }`.
 *
 * @return The original list if it has at least one element, otherwise `null`.
 */
fun <T> List<T>.takeIfNotEmpty(): List<T>? {
    return ifEmpty { null }
}
fun <T> List<T>.split(predicate: (T) -> Boolean): Pair<List<T>, List<T>> {
    return partition(predicate)
}
fun <T> Iterable<T>.sumByLong(selector: (T) -> Long): Long {
    return fold(0L) { sum, element -> sum + selector(element) }
}

/**
 * Returns a new list with all occurrences of the specified [oldValue] replaced by the [newValue].
 *
 * @param oldValue The value to be replaced.
 * @param newValue The value to replace with.
 * @return A new list with the replacements made.
 */
fun <T> List<T>.replaceAll(oldValue: T, newValue: T): List<T> {
    return map { if (it == oldValue) newValue else it }
}

/**
 * Splits this collection into a list of lists, where each inner list is a chunk of elements
 * separated by an element that matches the given [predicate]. The separator element itself
 * is not included in the resulting chunks.
 *
 * For example, `[1, 2, 0, 3, 4, 0, 5].chunkedBy { it == 0 }` would result in
 * `[[1, 2], [3, 4], [5]]`.
 *
 * An empty list is returned if the original list is empty. If no elements match the
 * predicate, a single list containing all elements of the original list is returned.
 *
 * @param predicate A function that returns `true` for elements that should be treated as separators.
 * @return A list of lists, where each list contains the elements between separators.
 */
fun <T> List<T>.chunkedBy(predicate: (T) -> Boolean): List<List<T>> {
    val result = mutableListOf<List<T>>()
    var currentChunk = mutableListOf<T>()

    forEach { item ->
        if (predicate(item) && currentChunk.isNotEmpty()) {
            result.add(currentChunk)
            currentChunk = mutableListOf()
        }
        currentChunk.add(item)
    }

    if (currentChunk.isNotEmpty()) {
        result.add(currentChunk)
    }

    return result
}
/**
 * Returns a set containing all elements that are contained more than once in this collection.
 *
 * @return A [Set] of the duplicate elements.
 */
fun <T> List<T>.duplicates(): List<T> {
    return groupingBy { it }
        .eachCount()
        .filter { it.value > 1 }
        .keys
        .toList()
}
