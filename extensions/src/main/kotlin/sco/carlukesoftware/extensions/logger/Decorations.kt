package sco.carlukesoftware.extensions.logger

import kotlin.time.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.util.UUID
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

val consoleLogger = Logger{ message ->
    println(message)
}

fun Logger.withUniqueId() = Logger { message ->
    log("{${UUID.randomUUID()}} $message")
}

/**
 * Creates a new logger that decorates messages with the name of the thread
 * where the log was invoked.
 *
 * Example usage:
 * ```kotlin
 * val logger = consoleLogger.withThreadName()
 * logger.log("Doing work") // Prints: Doing work (on main thread)
 * ```
 * @return A new [Logger] instance that adds thread information to each message.
 */
fun Logger.withThreadName() = Logger { message ->
    log("$message (on ${Thread.currentThread().name} thread)")
}

@OptIn(ExperimentalTime::class)
fun Logger.withDateTime(clock: Clock = Clock.System) = Logger { message ->
    // Get the current moment in time
    val currentMoment: Instant = Clock.System.now()

    // Convert the current moment to a LocalDateTime in a specific time zone
    val timeZone = TimeZone.currentSystemDefault()
    val dateTime: LocalDateTime = currentMoment.toLocalDateTime(timeZone)

    log("[${dateTime}: $message")
}
