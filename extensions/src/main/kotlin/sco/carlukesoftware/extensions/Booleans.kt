package sco.carlukesoftware.extensions

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

fun Boolean.whenTrue(block: (Boolean) -> Unit): Boolean {
    if (this) block(this)
    return this
}

fun Boolean.whenFalse(block: (Boolean) -> Unit): Boolean {
    if (!this) block(this)
    return this
}

@OptIn(ExperimentalContracts::class)
fun Boolean?.isTrue(): Boolean {
    contract {
        returns(true) implies (this@isTrue != null)
    }

    return this == true
}

@OptIn(ExperimentalContracts::class)
fun Boolean?.isFalse(): Boolean {
    contract {
        returns(true) implies (this@isFalse != null)
    }

    return this == false
}

val Boolean?.orTrue: Boolean
    get() = this ?: true

val Boolean?.orFalse: Boolean
    get() = this ?: false
