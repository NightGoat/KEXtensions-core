package ru.nightgoat.kextensionstests

import io.github.nightgoat.kexcore.tryOrDefault
import io.github.nightgoat.kexcore.tryOrEmpty
import io.github.nightgoat.kexcore.tryOrNull
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class HighOrderFunctionsTest {

    @Test
    fun tryOrDefault_test_1() {
        val foo = tryOrDefault(1) {
            throw NullPointerException()
        }
        assertEquals(1, foo)
    }

    @Test
    fun tryOrDefault_test_2() {
        val foo = tryOrDefault(1) {
            2
        }
        assertEquals(2, foo)
    }

    @Test
    fun tryOrNull_test_1() {
        val foo = tryOrNull {
            throw NullPointerException()
        }
        assertNull(foo)
    }

    @Test
    fun tryOrNull_test_2() {
        val foo = tryOrNull {
            1
        }
        assertEquals(1, foo)
    }

    @Test
    fun tryOrEmpty_test_1() {
        val foo = tryOrEmpty {
            throw NullPointerException()
        }
        assertEquals("", foo)
    }

    @Test
    fun tryOrEmpty_test_2() {
        val foo = tryOrEmpty {
            "bar"
        }
        assertEquals("bar", foo)
    }
}