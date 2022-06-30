package ru.nightgoat.kextensionstests

import ru.nightgoat.kexcore.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class BooleanExtTest {

    private var _null: Boolean? = null

    @Test
    fun orTrue_test_1() {
        assertEquals(true, _null.orTrue())
    }

    @Test
    fun orTrue_test_2() {
        assertEquals(false, false.orTrue())
    }

    @Test
    fun orFalse_test_1() {
        assertEquals(false, _null.orFalse())
    }

    @Test
    fun orFalse_test_2() {
        assertEquals(true, true.orFalse())
    }

    @Test
    fun takeIfTrue_test_1() {
        assertNotNull(true.takeIfTrue())
    }

    @Test
    fun takeIfTrue_test_2() {
        assertNull(false.takeIfTrue())
    }

    @Test
    fun takeIfFalse_test_1() {
        assertNotNull(false.takeIfFalse())
    }

    @Test
    fun takeIfFalse_test_2() {
        assertNull(true.takeIfFalse())
    }

    @Test
    fun doIfTrue_test_1() {
        var foo = 2
        true.doIfTrue {
            foo += 2
        }
        assertEquals(4, foo)
    }

    @Test
    fun doIfTrue_test_2() {
        var foo = 2
        false.doIfTrue {
            foo += 2
        }
        assertEquals(2, foo)
    }

    @Test
    fun doIfFalse_test_1() {
        var foo = 2
        false.doIfFalse {
            foo += 2
        }
        assertEquals(4, foo)
    }

    @Test
    fun doIfFalse_test_2() {
        var foo = 2
        true.doIfFalse {
            foo += 2
        }
        assertEquals(2, foo)
    }

    @Test
    fun toBinary_test_1() {
        val foo = true.toBinary()
        assertEquals(1, foo)
    }

    @Test
    fun toBinary_test_2() {
        val foo = false.toBinary()
        assertEquals(0, foo)
    }
}