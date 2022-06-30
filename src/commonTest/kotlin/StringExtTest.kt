package ru.nightgoat.kextensionstests

import ru.nightgoat.kexcore.*
import kotlin.test.*

class StringExtTest {

    private val digitString = "42"
    private val emptyString = ""
    private val notEmptyString = "foo"
    private var _null: String? = null

    @Test
    fun orIfEmpty_test_1() {
        assertEquals(notEmptyString, emptyString.orIfEmpty { "foo" })
    }

    @Test
    fun orIfEmpty_test_2() {
        assertEquals(notEmptyString, notEmptyString.orIfEmpty { "bar" })
    }

    @Test
    fun toDoubleOrZero_test_1() {
        assertEquals(42.0, digitString.toDoubleOrZero(), 0.0)
    }

    @Test
    fun toDoubleOrZero_test_2() {
        assertEquals(0.0, notEmptyString.toDoubleOrZero(), 0.0)
    }

    @Test
    fun toDoubleOrDefault_test_1() {
        assertEquals(123.0, notEmptyString.toDoubleOrDefault(123.0), 0.0)
    }

    @Test
    fun toDoubleOrDefault_test_2() {
        assertEquals(42.0, digitString.toDoubleOrDefault(123.0), 0.0)
    }

    @Test
    fun toIntOrZero_test_1() {
        assertEquals(42, digitString.toIntOrZero())
    }

    @Test
    fun toIntOrZero_test_2() {
        assertEquals(0, notEmptyString.toIntOrZero())
    }

    @Test
    fun toIntOrDefault_test_1() {
        assertEquals(123, notEmptyString.toIntOrDefault(123))
    }

    @Test
    fun toIntOrDefault_test_2() {
        assertEquals(42, digitString.toIntOrDefault(123))
    }

    @Test
    fun toLongOrZero_test_1() {
        assertEquals(42L, digitString.toLongOrZero())
    }

    @Test
    fun toLongOrZero_test_2() {
        assertEquals(0L, notEmptyString.toLongOrZero())
    }

    @Test
    fun toLongOrDefault_test_1() {
        assertEquals(123L, notEmptyString.toLongOrDefault(123L))
    }

    @Test
    fun toLongOrDefault_test_2() {
        assertEquals(42L, digitString.toLongOrDefault(123L))
    }

    @Test
    fun trimZeros_test_1() {
        assertEquals("42", "00042".trimZeros())
    }

    @Test
    fun trimZeros_test_2() {
        assertEquals("42", "42".trimZeros())
    }

    @Test
    fun orZero_test_1() {
        assertEquals("0", _null.orZero())
    }

    @Test
    fun orZero_test_2() {
        assertEquals(notEmptyString, notEmptyString.orZero())
    }

    @Test
    fun takeIfNotEmpty_test_1() {
        assertNull(emptyString.takeIfNotEmpty())
    }

    @Test
    fun takeIfNotEmpty_test_2() {
        assertNotNull(notEmptyString.takeIfNotEmpty())
    }

    @Test
    fun takeIfEmpty_test_1() {
        assertNull(notEmptyString.takeIfEmpty())
    }

    @Test
    fun takeIfEmpty_test_2() {
        assertNotNull(emptyString.takeIfEmpty())
    }

    @Test
    fun isWhiteSpace_test_1() {
        assertEquals(true, emptyString.isWhiteSpace())
    }

    @Test
    fun isWhiteSpace_test_2() {
        assertEquals(false, notEmptyString.isWhiteSpace())
    }

    @Test
    fun isWhiteSpace_test_3() {
        assertEquals(true, " ".isWhiteSpace())
    }

    @Test
    fun normalize_test_1() {
        assertEquals("Hello world!", "HellO wOrld!".normalize())
    }

    @Test
    fun isEmail_test_1() {
        assertTrue("test@test.com".isEmail())
    }

    @Test
    fun isEmail_test_2() {
        assertFalse("test.com".isEmail())
    }

    @Test
    fun isPhone_test_1() {
        assertTrue("+79211234567".isPhone())
    }

    @Test
    fun isPhone_test_2() {
        assertFalse("ABC".isPhone())
    }

    @Test
    fun isIPAddress_test_1() {
        assertTrue("192.168.0.1".isIPAddress())
    }

    @Test
    fun isIPAddress_test_2() {
        assertFalse("Hello world!".isIPAddress())
    }
}