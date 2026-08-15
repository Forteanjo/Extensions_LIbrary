package sco.carlukesoftware.extensionslibrary

import org.junit.Test
import sco.carlukesoftware.extensions.isNotNull
import sco.carlukesoftware.extensions.isNull
import sco.carlukesoftware.extensions.isTypeOfOrNull
import sco.carlukesoftware.extensions.shouldBe
import sco.carlukesoftware.extensions.shouldContain


class AnyExtensionsTest {

    @Test
    fun testAnyToString() {
        val any: Any? = null
        any.toString() shouldBe "null"

    }

    @Test
    fun testAnyIsNull() {
        val any: Any? = null

        any.isNull shouldBe true
        any.isNotNull shouldBe false
    }

    @Test
    fun testAnyShouldContain() {
        val any: Any = 12345
        any.toString() shouldBe "12345"
        any.toString() shouldContain "234"
    }

    @Test
    fun testAnyTypeOf() {
        val any: Any? = null
        any.isTypeOfOrNull<String>() shouldBe true
        any.isTypeOfOrNull<Any>() shouldBe true
    }
}
