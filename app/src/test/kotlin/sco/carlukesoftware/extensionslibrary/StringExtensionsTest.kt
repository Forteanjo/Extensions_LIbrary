package sco.carlukesoftware.extensionslibrary

import org.junit.Test
import sco.carlukesoftware.extensions.isEmailValid
import sco.carlukesoftware.extensions.shouldBe
import sco.carlukesoftware.extensions.times
import sco.carlukesoftware.extensions.wordCount

class StringExtensionsTest {

    @Test
    fun testisEmailValid() {
        "test@test.com".isEmailValid() shouldBe true
        "test@test".isEmailValid() shouldBe false
    }

    @Test
    fun testTimes() {
        val repeatedString = "abc" * 3
        repeatedString shouldBe "abcabcabc"
    }

    @Test
    fun testTimesZero() {
        val repeatedString = "abc" * 0
        repeatedString shouldBe ""
    }

    @Test
    fun testWordCount() {
        val wordCount = "Hello, World!".wordCount
        wordCount shouldBe 2
    }

}
