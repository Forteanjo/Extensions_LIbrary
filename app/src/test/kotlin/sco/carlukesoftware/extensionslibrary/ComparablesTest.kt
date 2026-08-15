package sco.carlukesoftware.extensionslibrary

import org.junit.Test
import sco.carlukesoftware.extensions.overlaps

class ComparablesTest {

    @Test
    fun testContains() {
        val range = 1..10
        assert(5 in range)
        assert(0 !in range)
    }

    @Test
    fun testOverlaps() {
        val range1 = 1..10
        val range2 = 5..15
        val range3 = 25..30
        assert(range1.overlaps(range2))
        assert(range2.overlaps(range1))
        assert(!range1.overlaps(range3))
    }
}
