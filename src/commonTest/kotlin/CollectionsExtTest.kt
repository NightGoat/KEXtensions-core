package ru.nightgoat.kextensionstests

import io.github.nightgoat.kexcore.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class CollectionsExtTest {

    @Test
    fun orEmptyMutable_list_test_1() {
        val list = listOf(1, 2, 3)
        assertEquals(mutableListOf(1, 2, 3), list.orEmptyMutable())
    }

    @Test
    fun orEmptyMutable_list_test_2() {
        val list: List<Int>? = null
        assertEquals(mutableListOf<Int>(), list.orEmptyMutable())
    }

    @Test
    fun orEmptyMutable_set_test_1() {
        val set = setOf(1, 2, 3)
        assertEquals(mutableSetOf(1, 2, 3), set.orEmptyMutable())
    }

    @Test
    fun orEmptyMutable_set_test_2() {
        val set: Set<Int>? = null
        assertEquals(mutableSetOf<Int>(), set.orEmptyMutable())
    }

    @Test
    fun orEmptyMutable_map_test_1() {
        val map = mapOf(
            1 to 1,
            2 to 2
        )
        assertEquals(mutableMapOf(1 to 1, 2 to 2), map.orEmptyMutable())
    }

    @Test
    fun orEmptyMutable_map_test_2() {
        val map: Map<Int, Int>? = null
        assertEquals(mutableMapOf<Int, Int>(), map.orEmptyMutable())
    }

    @Test
    fun orEmptyMutableMap_test_1() {
        val list = listOf<Pair<Int, Int>>(Pair(1, 1), Pair(2, 2))
        assertEquals(mutableMapOf(1 to 1, 2 to 2), list.orEmptyMutableMap())
    }

    @Test
    fun orEmptyMutableMap_test_2() {
        val list: List<Pair<Int, Int>>? = null
        assertEquals(mutableMapOf<Int, Int>(), list.orEmptyMutableMap())
    }

    @Test
    fun indexOfOrNull_test_1() {
        val list = listOf(1, 2, 3)
        assertEquals(0, list.indexOfOrNull(1))
    }

    @Test
    fun indexOfOrNull_test_2() {
        val list = listOf(1, 2, 3)
        assertNull(list.indexOfOrNull(4))
    }

    @Test
    fun addIf_test_1() {
        val list = mutableListOf(1, 2, 3)
        list.addIf(list.isNotEmpty()) { 4 }
        assertEquals(list.size, 4)
    }

    @Test
    fun addIf_test_2() {
        val list = mutableListOf(1, 2, 3)
        list.addIf(list.size > 3) { 4 }
        assertEquals(list.size, 3)
    }

    @Test
    fun addIf_test_3() {
        val list = mutableListOf(1, 2, 3)
        list.addIf(4) {
            it.isNotEmpty()
        }
        assertEquals(list.size, 4)
    }

    @Test
    fun addIf_test_4() {
        val list = mutableListOf(1, 2, 3)
        list.addIf(4) {
            it.size > 3
        }
        assertEquals(list.size, 3)
    }

    @Test
    fun distinctAndFilter_test_1() {
        val list = mutableListOf(Pair(1, 2), Pair(1, 3), Pair(2, 4))
        val filtered = list.distinctAndFilter(
            distinctBy = {
                it.first
            },
            filterBy = {
                it.second < 4
            }
        )
        assertEquals(mutableListOf(Pair(1, 2)), filtered)
    }

    @Test
    fun firstOrElse_test_1() {
        val empty = emptyList<Int>()
        assertEquals(0, empty.firstOrElse { 0 })
    }

    @Test
    fun firstOrElse_test_2() {
        val notEmpty = listOf<Int>(1, 2)
        assertEquals(1, notEmpty.firstOrElse { 0 })
    }

    @Test
    fun sizeInDouble_test_1() {
        val list = listOf(1, 2)
        assertEquals(2.0, list.sizeInDouble(), 0.0)
    }

    @Test
    fun sizeInString_test_1() {
        val list = listOf(1, 2)
        assertEquals("2", list.sizeInString())
    }

    @Test
    fun findInInstanceOf_test_1() {
        val list: List<Number> = listOf(1, 10, 2.0, 3L)
        val founded = list.findInInstanceOf<Int> { it < 10 }
        assertEquals(1, founded)
    }

    @Test
    fun findInInstanceOf_test_2() {
        val list: List<Number> = listOf(1, 10, 3L)
        val founded = list.findInInstanceOf<Double> { it < 10 }
        assertNull(founded)
    }

    @Test
    fun mapNotNullOrEmpty_test_1() {
        val list: List<Int?>? = null
        assertEquals(emptyList<Int>(), list.mapNotNullOrEmpty {
            it?.let {
                it * 2
            }
        })
    }

    @Test
    fun mapNotNullOrEmpty_test_2() {
        val list: List<Int?> = listOf(0, 1, 2, null)
        assertEquals(listOf(0, 2, 4), list.mapNotNullOrEmpty {
            it?.let {
                it * 2
            }
        })
    }

    @Test
    fun mapOrEmpty_test_1() {
        val list: List<Int?>? = null
        assertEquals(emptyList<Int>(), list.mapOrEmpty {
            it?.let {
                it * 2
            }
        })
    }

    @Test
    fun mapOrEmpty_test_2() {
        val list: List<Int?> = listOf(0, 1, 2, null)
        assertEquals(listOf(0, 2, 4, null), list.mapOrEmpty {
            it?.let {
                it * 2
            }
        })
    }

    @Test
    fun mapAndFind_test_1() {
        data class Shape(val edges: Int, val angle: Int = 0, val color: Int)

        val circle = Shape(edges = 0, color = 1)
        val square = Shape(edges = 4, color = 2)
        val rhombus = Shape(edges = 4, angle = 45, color = 3)
        val triangle = Shape(edges = 3, color = 4)
        val shapes = listOf(circle, square, rhombus, triangle)
        val yellowSquareSequence = shapes.mapAndFind(
            map = {
                it.copy(color = 3)
            },
            find = {
                it.edges == 4
            }
        )
        assertEquals(yellowSquareSequence, Shape(edges = 4, angle = 0, color = 3))
    }
}