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

inline fun <T> List<T>?.whenNotEmpty(block: (List<T>) -> Unit) {
    if (!this.isNullOrEmpty()) block(this)
}

fun <T> List<T>?.or(index: Int, default: T): T =
    if (this != null && index in indices) this[index] else default

inline fun <T, R> List<T>?.mapIfNotEmpty(transform: (T) -> R): List<R> =
    if (isNullOrEmpty()) emptyList() else map(transform)
