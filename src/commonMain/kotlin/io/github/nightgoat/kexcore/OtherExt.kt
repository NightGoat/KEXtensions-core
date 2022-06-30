package io.github.nightgoat.kexcore

import io.github.nightgoat.kexcore.utils.Kex

/**
 * Helper nullability function
 */
inline fun <reified T> T?.orIfNull(input: () -> T): T {
    return this ?: input()
}

/**
 * unsafe lazy delegate realisation
 */
inline fun <reified T, reified R> R.unsafeLazy(noinline init: () -> T): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE, init)
}

fun <T, V> T?.orLet(variable: V?, letBlock: (V) -> T) = this ?: variable?.let(letBlock::invoke)

/**
 * Method that simplifies logging null objects.
 * Best way to use:
 * fun foo() {
 *   object?.let {
 *     object.doWork()
 *   }.logIfNull("foo(): object null, "Bar"")
 * }
 */
fun <T : Any> T?.logIfNull(message: String, tag: String = "logIfNull"): T? {
    if (this == null) {
        Kex.loggE(message, tag)
    }
    return this
}

/**
 * Extention that simplifies null checking
 * example:
 * class Box {
 *    val findProduct(): Product
 * }
 *
 * class Foo {
 *     val box = Repo.getBox()
 *     val product = box.findProduct()
 *     if (product.exists()) {
 *         doStuff()
 *     }
 * }
 * */
fun <T> T?.exists() = this != null

/**
 * Turns object to list of this one object inside or empty if object is null.
 */
fun <T : Any> T?.asList() = this?.run { listOf(this) }.orEmpty()

/**
 * Turns object to mutable list of this one object inside or empty if object is null.
 */
fun <T : Any> T?.asMutableList() = this?.run { mutableListOf(this) } ?: mutableListOf()
