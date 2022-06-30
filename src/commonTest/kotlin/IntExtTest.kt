package ru.nightgoat.kextensionstests

import ru.nightgoat.kexcore.orZero
import ru.nightgoat.kexcore.reverse
import ru.nightgoat.kexcore.toNegative
import ru.nightgoat.kexcore.toPositive
import kotlin.test.Test
import kotlin.test.assertEquals

class IntExtTest {

    private val foo = 42
    private val negativeFoo = -42

    @Test
    fun orZeroTest_1() {
        val nullableInt: Int? = null
        assertEquals(0, nullableInt.orZero())
    }

    @Test
    fun orZeroTest_2() {
        var nullableInt: Int? = null
        nullableInt = 42
        assertEquals(42, nullableInt.orZero())
    }

    @Test
    fun toNegativeTest_1() {
        assertEquals(-42, foo.toNegative())
    }

    @Test
    fun toNegativeTest_2() {
        assertEquals(-42, negativeFoo.toNegative())
    }

    @Test
    fun toPositiveTest_1() {
        assertEquals(42, negativeFoo.toPositive())
    }

    @Test
    fun toPositiveTest_2() {
        assertEquals(42, foo.toPositive())
    }

    @Test
    fun reverseTest_1() {
        assertEquals(-42, foo.reverse())
    }

    @Test
    fun reverseTest_2() {
        assertEquals(-42, foo.reverse())
    }

    @Test
    fun reverseTest_3() {
        assertEquals(42, foo.reverse().reverse())
    }
}