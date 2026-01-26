package sco.carlukesoftware.extensions.delegates

import kotlin.reflect.KProperty

/**
 * A simple demonstration class for a delegated property.
 *
 * This class implements the `getValue` and `setValue` operator functions,
 * allowing its instances to be used as delegates for `var` properties of type `String`.
 *
 * Example of usage:
 * ```kotlin
 * class Example {
 *     var p: String by Delegate()
 * }
 *
 * val e = Example()
 * println(e.p) // Prints: "Example@..., thank you for delegating 'p' to me"
 * e.p = "NEW"  // Prints: "NEW has been assigned to 'p' in Example@..."
 * ```
 */
class Delegate {
    /**
     * Retrieves the value of the property. This is the standard `getValue` operator function
     * for a property delegate.
     *
     * @param thisRef the object from which the value is being read.
     * @param property the metadata for the property.
     * @return A descriptive [String] confirming the delegation.
     */
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me"
    }

    /**
     * Sets the value of the property and prints a message to the standard output.
     *
     * @param thisRef the object from which the property is being set.
     * @param property the metadata of the property being set.
     * @param value the new value to be assigned to the property.
     */
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef")
    }
}
