package sco.carlukesoftware.extensionslibrary

import org.junit.Test
import sco.carlukesoftware.extensions.get
import sco.carlukesoftware.extensions.isPrime

class NumbersTest {

    @Test
    fun isPrimeTest() {
        assert(2L.isPrime())
        assert(3L.isPrime())
        assert(5L.isPrime())
        assert(7L.isPrime())
        assert(11L.isPrime())
        assert(13L.isPrime())
        assert(17L.isPrime())
        assert(19L.isPrime())
        assert(23L.isPrime())
        assert(29L.isPrime())
    }
    @Test
    fun isNotPrimeTest() {
        assert(!1L.isPrime())
        assert(!4L.isPrime())
        assert(!6L.isPrime())
        assert(!8L.isPrime())
    }

    @Test
    fun getDigitTest() {
        assert(12345[0] == 5)
        assert(12345[1] == 4)
        assert(12345[2] == 3)
    }

    @Test
    fun getDigitOutOfBoundsTest() {
        assert(12345[-1] == 0)
        assert(12345[5] == 0)
    }


}
