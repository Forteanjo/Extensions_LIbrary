package sco.carlukesoftware.extensionslibrary

import org.junit.Test
import sco.carlukesoftware.extensions.merge
import sco.carlukesoftware.extensions.percentage
import sco.carlukesoftware.extensions.shouldBe
import sco.carlukesoftware.extensions.takePercentage

class CollectionsTest {

    @Test
    fun testTakePercentage() {
        val collection = listOf(1, 2, 3, 4, 5).takePercentage(0.5)
        collection shouldBe listOf(1, 2, 3)
    }

    @Test
    fun testMerge() {
        val map1 = mapOf("a" to 1, "b" to 2)
        val map2 = mapOf("a" to 3, "c" to 4)
        val mergedMap = listOf(map1, map2).merge { it.sum() }
        mergedMap shouldBe mapOf("a" to 4, "b" to 2, "c" to 4)
    }

    @Test
    fun testPercentage() {
        val collection = listOf(1, 2, 3, 4, 5)
        val percentage = collection.percentage { it % 2 == 0 }
        percentage shouldBe 0.4f
    }

}
