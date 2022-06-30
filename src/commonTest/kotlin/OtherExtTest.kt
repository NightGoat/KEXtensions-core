package ru.nightgoat.kextensionstests

import io.github.nightgoat.kexcore.exists
import io.github.nightgoat.kexcore.orIfNull
import io.github.nightgoat.kexcore.orLet
import kotlin.test.Test
import kotlin.test.assertEquals

class OtherExtTest {

    @Test
    fun orIfNull_test_1() {
        val foo: Int? = null
        val bar = foo.orIfNull { 2 }
        assertEquals(2, bar)
    }

    @Test
    fun orIfNull_test_2() {
        val foo = 2
        val bar = foo.orIfNull { 3 }
        assertEquals(2, bar)
    }

    @Test
    fun orLet_test_1() {
        val foo: Int? = null
        val bar = 12
        fun baz(value: Int): Int {
            return value * 2
        }

        val far = foo.orLet(bar, ::baz)
        assertEquals(24, far)
    }

    @Test
    fun exists_test_1() {
        val foo: Int? = null
        assertEquals(false, foo.exists())
    }

    @Test
    fun exists_test_2() {
        val foo = 1
        assertEquals(true, foo.exists())
    }
}