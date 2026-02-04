package sco.carlukesoftware.extensions.logger

/**
 * A functional interface for logging messages.
 *
 * This interface defines a single abstract method, `log`, which can be implemented
 * by a lambda expression to provide a simple logging mechanism.
 *
 * Example usage:
 * ```kotlin
 * val consoleLogger = Logger { message -> println("LOG: $message") }
 * consoleLogger.log("This is a test message.")
 * ```
 */
fun interface Logger {
    fun log(message: String)
}
