package ru.nightgoat.kextensionstests

import io.github.nightgoat.kexcore.orZero
import io.github.nightgoat.kexcore.roundTo
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class DoubleExtTest {

    @Test
    fun orZero_test_1() {
        val foo: Double? = null
        assertEquals(0.0, foo.orZero(), 0.0)
    }

    @Test
    fun orZero_test_2() {
        val foo = 42.0
        assertEquals(42.0, foo.orZero(), 0.0)
    }

    @Test
    fun roundTo_test_1() {
        val foo = 0.12345
        assertEquals(0.12, foo.roundTo(2), 0.0)
    }

    @Test
    fun roundTo_test_2() {
        val foo = 0.12345
        assertNotEquals(0.12, foo.roundTo(3), 0.0)
    }
}