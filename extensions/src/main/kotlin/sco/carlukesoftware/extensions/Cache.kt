package sco.carlukesoftware.extensions

/**
 * A simple in-memory cache implementation.
 *
 * This class provides a basic key-value store for caching objects in memory.
 * It is not thread-safe and does not implement any eviction policies.
 *
 * @param K The type of the keys in the cache.
 * @param V The type of the values in the cache.
 */
class Cache<K, V> {

    private val cache = mutableMapOf<K, V>()

    fun put(key: K, value: V) {
        cache[key] = value
    }

    fun get(key: K): V? = cache[key]

    fun clear() {
        cache.clear()
    }

    fun remove(key: K) {
        cache.remove(key)
    }

    fun containsKey(key: K): Boolean = key in cache

}
