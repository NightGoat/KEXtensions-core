package io.github.nightgoat.kexcore

import io.github.nightgoat.kexcore.utils.Try

/**
 * try default fun realisation
 */
fun <T : Any> tryOrDefault(
    defaultValue: T,
    tag: String = "tryOrDefault(): ",
    tryFunc: () -> T?
): T {
    return Try.of(tag) {
        tryFunc() ?: defaultValue
    }.getOrDefault {
        defaultValue
    }
}

fun <T : Any> tryOrNull(tag: String = "tryOrNull(): ", tryFunc: () -> T?): T? {
    return Try(tag) {
        tryFunc()
    }.getOrNull()
}

fun tryOrEmpty(tag: String = "tryOrEmpty(): ", tryFunc: () -> String) =
    tryOrDefault("", tag) { tryFunc() }