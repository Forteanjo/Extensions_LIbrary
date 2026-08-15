package sco.carlukesoftware.extensionslibrary

import org.junit.Test
import sco.carlukesoftware.extensions.isFalse
import sco.carlukesoftware.extensions.isTrue
import sco.carlukesoftware.extensions.orFalse
import sco.carlukesoftware.extensions.orTrue
import sco.carlukesoftware.extensions.shouldBe
import sco.carlukesoftware.extensions.toInt
import sco.carlukesoftware.extensions.whenFalse
import sco.carlukesoftware.extensions.whenTrue

class BooleansExtensionsTest {

    @Test
    fun testWhenTrue() {
        var result = false
        true.whenTrue { result = true }
        result shouldBe true
    }

    @Test
    fun testWhenFalse() {
        var result = true
        false.whenFalse { result = false }
        result shouldBe false
    }

    @Test
    fun testIsTrue() {
        true.isTrue() shouldBe true
        false.isTrue() shouldBe false
    }

    @Test
    fun testIsFalse() {
        true.isFalse() shouldBe false
        false.isFalse() shouldBe true
    }
    @Test
    fun testOrTrue() {
        true.orTrue shouldBe true
        false.orTrue shouldBe false
    }

    @Test
    fun testOrFalse() {
        true.orFalse shouldBe true
        false.orFalse shouldBe false
    }

    @Test
    fun testBooleanToInt() {
        true.toInt() shouldBe 1
        false.toInt() shouldBe 0
    }
}
