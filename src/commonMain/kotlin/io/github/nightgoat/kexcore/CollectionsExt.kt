package io.github.nightgoat.kexcore

import io.github.nightgoat.kexcore.utils.Kex

fun <T> List<T>?.orEmptyMutable(): MutableList<T> = this?.toMutableList() ?: mutableListOf()

fun <T> Set<T>?.orEmptyMutable(): MutableSet<T> = this?.toMutableSet() ?: mutableSetOf()

fun <T, K> Map<T, K>?.orEmptyMutable(): MutableMap<T, K> = this?.toMutableMap() ?: mutableMapOf()

fun <T, K> List<Pair<T, K>>?.orEmptyMutableMap(): MutableMap<T, K> =
    this?.toMutableMap() ?: mutableMapOf()

fun <K, V> Iterable<Pair<K, V>>.toMutableMap(): MutableMap<K, V> {
    if (this is Collection) {
        return when (size) {
            0 -> mutableMapOf()
            1 -> mutableMapOf(if (this is List) this[0] else iterator().next())
            else -> toMap(LinkedHashMap<K, V>(size))
        }
    }
    return toMap(LinkedHashMap())
}

fun <T> List<T>.indexOfOrNull(element: T) = this.indexOf(element).takeIf { it != -1 }

fun <T> MutableCollection<T>.addIf(predicate: Boolean, whatToAdd: () -> T) {
    if (predicate) this.add(whatToAdd())
}

fun <T> MutableCollection<T>.addIf(whatToAdd: T, predicate: (MutableCollection<T>) -> Boolean) {
    if (predicate.invoke(this)) this.add(whatToAdd)
}

fun <T, S : MutableCollection<T>> S.addOnlyNew(whatToAdd: Collection<T>): S {
    whatToAdd.forEach { element ->
        if (!this.contains(element)) {
            this.add(element)
        }
    }
    return this
}

fun <T : Any> Iterable<T>.findIndexed(predicate: (T) -> Boolean): Pair<Int, T>? {
    this.forEachIndexed { index, t ->
        if (predicate(t)) {
            return Pair(index, t)
        }
    }
    return null
}

fun <T : Any> Iterable<T>.findLastIndexed(predicate: (T) -> Boolean): Pair<Int, T>? {
    var last: Pair<Int, T>? = null

    this.forEachIndexed { index, t ->
        if (predicate(t)) {
            last = Pair(index, t)
        }
    }
    return last
}

/**
 * Distincts and filters Iterable in one cycle. Faster that using
 * list.distinctBy {  }.filter {  }
 */
inline fun <T, K> Iterable<T>.distinctAndFilter(
    distinctBy: (T) -> K,
    filterBy: (T) -> Boolean
): List<T> {
    val set = HashSet<K>()
    val list = ArrayList<T>()
    for (e in this) {
        val key = distinctBy(e)
        if (set.add(key) && filterBy(e))
            list.add(e)
    }
    return list
}

inline fun <reified T> Collection<T>.firstOrElse(elseFun: () -> T): T {
    return this.firstOrNull().orIfNull(elseFun)
}

/**
 * Returns size in instance of Double
 */
fun <T> Collection<T>.sizeInDouble(): Double {
    return this.size.toDouble()
}

/**
 * Returns size in instance of String
 */
fun <T> Collection<T>.sizeInString(): String {
    return this.size.toString()
}

inline fun <reified R> Iterable<*>.findInInstanceOf(
    predicate: (R) -> Boolean
): R? {
    for (element in this) if (element is R && predicate(element)) return element
    return null
}

inline fun <T, K> Collection<T>?.mapNotNullOrEmpty(transform: (T) -> K?): List<K> =
    this.orEmpty().mapNotNull {
        transform.invoke(it)
    }

inline fun <T, K> Collection<T>?.mapOrEmpty(transform: (T) -> K): List<K> = this.orEmpty().map {
    transform.invoke(it)
}

/**
 * Just and example of how to work with Sequence
 * @see <a href="http://google.com">https://nuancesprog.ru/p/4603/</a>
 * */
fun <T, R> Collection<T>.mapAndFind(map: (T) -> R, find: (R) -> Boolean) = this.asSequence()
    .map(map)
    .find(find)

/**
 * Swaps elements by entered positions and returns list. Returns not changed list, if second index
 * less or equals than first. Swaps first and last item by default.
 */
fun <T : Any> MutableCollection<T>.swap(
    firstIndex: Int = 0,
    secondIndex: Int = this.size - 1
): MutableList<T> {
    val list = this.toMutableList()
    if (secondIndex > firstIndex) {
        val first = list.getOrNull(firstIndex)
        val last = list.getOrNull(secondIndex)
        first?.let {
            last?.let {
                list.removeAt(firstIndex)
                list.removeAt(secondIndex - 1)
                list.add(firstIndex, last)
                list.add(secondIndex, first)
            } ?: Kex.loggE("swap(): item by second index($secondIndex) not found!")
        } ?: Kex.loggE("swap(): item by first index($firstIndex) not found!")
    } else {
        Kex.loggE("swap(): second index($secondIndex) can not be more than first index($firstIndex)!")
    }
    return list
}

/**
 * Swaps first and last item.
 */
fun <T : Any> MutableCollection<T>.swapFirstAndLastElement(): MutableList<T> {
    return this.swap()
}

/**
 * Finds object in list and moves to entered position. Moves to first position by default.
 */
fun <T : Any> MutableCollection<T>.moveToPosition(
    position: Int = 0,
    findItemBy: (T) -> Boolean
): MutableList<T> {
    val list = this.toMutableList()
    if (position in 0..list.lastIndex) {
        list.find { findItemBy(it) }?.let { found ->
            list.remove(found)
            list.add(position, found)
        }
    } else {
        Kex.loggE("moveToPosition(): position($position) must be in bounds of a list(in 0..${list.lastIndex}! ")
    }
    return list
}

/**
 * Finds object in list and moves to first position
 */
fun <T : Any> MutableCollection<T>.moveToFirstPosition(findItemBy: (T) -> Boolean): MutableList<T> {
    return moveToPosition(findItemBy = findItemBy)
}

/**
 * Finds object in list and moves to last position
 */
fun <T : Any> MutableCollection<T>.moveToLastPosition(findItemBy: (T) -> Boolean): MutableList<T> {
    return moveToPosition(position = this.size - 1, findItemBy = findItemBy)
}

fun <T : Any> MutableCollection<T>.replaceAt(newItem: T, index: Int) = this.mapIndexed { i, item ->
    if (index == i) {
        newItem
    } else {
        item
    }
}

fun <T : Any, S : Collection<T>> S.takeIfNotEmpty(): S? {
    return this.takeIf { this.isNotEmpty() }
}

fun <T : Any> Iterable<T>.changeElementBy(item: T, findItemBy: (T) -> Boolean) = this.map {
    if (findItemBy(it)) {
        item
    } else {
        it
    }
}

fun <T : Any> Iterable<T>.changeElementBy(itemModification: (T) -> T, findItemBy: (T) -> Boolean) = this.map {
    if (findItemBy(it)) {
        itemModification(it)
    } else {
        it
    }
}