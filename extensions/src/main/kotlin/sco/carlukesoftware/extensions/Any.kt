package sco.carlukesoftware.extensions

import android.util.Log
import kotlin.reflect.KClass

//Extension functions for all classes
fun <T: Any> T.getClass(): KClass<T> {
    return javaClass.kotlin
}

inline val Any.TAG: String
    get() = this::class.java.toString()

inline fun Any?.onNotNull(block: ()-> Unit) = if (this != null) { block() } else Unit

inline fun Any?.onIsNull(block: () -> Unit) = if (this == null) { block() } else Unit

inline fun Any?.toString(): String = this?.toString() ?: "null"

val Any?.isNull: Boolean get() = this == null
val Any?.isNotNull: Boolean get() = this != null

fun Any?.printToLog(tag: String = "DEBUG_LOG") {
    Log.d(tag, toString())
}

inline fun <reified T> Any?.isTypeOfOrNull() = this is T?
