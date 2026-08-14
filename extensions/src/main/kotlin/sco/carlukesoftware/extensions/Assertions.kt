package sco.carlukesoftware.extensions

/**
 * Asserts that the provided [block] throws an exception of type [T].
 *
 * This helper function executes the given code block and verifies that an exception
 * of the specified reified type is raised. If no exception is thrown, or if an
 * exception of a different type is thrown, the test will fail.
 *
 * @param T The expected type of the [Throwable].
 */
inline fun <reified T : Throwable> shouldThrow(block: () -> Unit): T {
    try {
        block()
    } catch (e: Throwable) {
        if (e is T) return e
        throw AssertionError("Expected ${T::class.simpleName} but caught ${e::class.simpleName}")
    }
    throw AssertionError("Expected ${T::class.simpleName} but no exception was thrown")
}

/**
 * Asserts that the receiver is not null.
 *
 * @return The non-null value.
 * @throws AssertionError if the value is null.
 */
fun <T : Any> T?.shouldNotBeNull(): T {
    if (this == null) {
        throw AssertionError("Expected value to be non-null")
    }
    return this
}

/**
 * Asserts that the receiver string contains the specified [substring].
 *
 * @param substring The string expected to be present.
 * @throws AssertionError if the string does not contain the substring.
 */
infix fun String.shouldContain(substring: String) {
    if (!this.contains(substring)) {
        throw AssertionError("Expected string to contain \"$substring\" but was \"$this\"")
    }
}

/**
 * Asserts that the receiver is equal to the [expected] value.
 *
 * @param expected The expected value.
 * @throws AssertionError if the values are not equal.
 */
infix fun <T> T.shouldBe(expected: T) {
    if (this != expected) {
        throw AssertionError("Expected: $expected\nActual: $this")
    }
}
