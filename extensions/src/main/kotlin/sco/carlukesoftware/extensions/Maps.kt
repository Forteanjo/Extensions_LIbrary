package sco.carlukesoftware.extensions

/**
 * A generic interface for mapping an object of type [F] (from) to an object of type [T] (to).
 *
 * This interface uses an operator function `invoke`, allowing instances to be called like functions.
 *
 * Example usage:
 * ```
 * class StringToIntMapper : Mapper<String, Int> {
 *     override fun invoke(from: String): Int {
 *         return from.toIntOrNull() ?: 0
 *     }
 * }
 *
 * val mapper = StringToIntMapper()
 * val result = mapper("123") // result is 123
 * ```
 *
 * @param F The source type to map from. The `in` variance modifier indicates that [F] can only be consumed (used as a parameter).
 * @param T The target type to map to. The `out` variance modifier indicates that [T] can only be produced (used as a return type).
 */
interface Mapper<in F, out T> {

    operator fun invoke(from: F): T

}
