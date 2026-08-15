package sco.carlukesoftware.extensionslibrary

import junit.framework.TestCase.assertEquals
import org.junit.Test
import sco.carlukesoftware.extensions.celsiusToFahrenheit
import sco.carlukesoftware.extensions.fahrenheitToCelsius
import sco.carlukesoftware.extensions.shouldBe

class ConversionsTest {
    @Test
    fun testCelsiusToFahrenheit() {
        val celsius = 25.0f
        val fahrenheit = celsius.celsiusToFahrenheit()

        assertEquals(77.0f, fahrenheit, 0.1f)
    }

    @Test
    fun testFahrenheitToCelsius() {
        val fahrenheit = 77.0f
        val celsius = fahrenheit.fahrenheitToCelsius()
        assertEquals(25.0f, celsius, 0.1f)
    }
}
