package io.github.nightgoat.kexcore

import io.github.nightgoat.kexcore.utils.constants.KexConstants
import io.github.nightgoat.kexcore.utils.constants.KexConstants.EMAIL_PATTERN
import io.github.nightgoat.kexcore.utils.constants.KexConstants.IP_ADDRESS_PATTERN
import io.github.nightgoat.kexcore.utils.constants.KexConstants.PHONE_PATTERN
import io.github.nightgoat.kexcore.utils.constants.KexConstants.ZERO_STRING

fun String.orIfEmpty(input: () -> String): String {
    return if (this.isEmpty()) input.invoke()
    else this
}

fun String?.toDoubleOrZero() = this?.toDoubleOrNull() ?: 0.0

fun String?.toDoubleOrDefault(defaultValue: Double): Double {
    return this?.toDoubleOrNull() ?: defaultValue
}

fun String?.toIntOrZero() = this?.toIntOrNull() ?: 0

fun String?.toIntOrDefault(defaultValue: Int): Int {
    return this?.toIntOrNull() ?: defaultValue
}

fun String?.toLongOrZero() = this?.toLongOrNull() ?: 0L

fun String?.toLongOrDefault(defaultValue: Long): Long {
    return this?.toLongOrNull() ?: defaultValue
}

/**
 * returns True if string matches input symbol. Input symbol is "X" by default.
 */
fun String?.isTrue(trueSymbol: String = "X"): Boolean {
    return this == trueSymbol
}

fun String?.toBooleanFrom(trueSymbol: String = "X", falseSymbol: String = ""): Boolean? {
    return when (this) {
        trueSymbol -> true
        falseSymbol -> false
        else -> null
    }
}

fun String?.isTrueBinary() = this.isTrue("1")

fun String?.isTrueBinaryOrNull(): Boolean? {
    return this.toBooleanFrom("1", "0")
}


fun String.trimZeros() = this.trimStart('0')

fun String?.orZero() = this ?: ZERO_STRING

fun String.takeIfNotEmpty() = this.takeIf { it.isNotEmpty() }

fun String.takeIfEmpty() = this.takeIf { it.isEmpty() }

fun String?.isWhiteSpace() = this?.all { it.isWhitespace() } == true

/**
 * Returns string in normal format
 * example:
 * val foo = "HellO wOrld!"
 * val baz = foo.normalize() -> returns "Hello world!"
 * */
fun String.normalize() = this.lowercase().replaceFirstChar {
    it.titlecase()
}

fun String.isEmail(): Boolean {
    val pattern = Regex(EMAIL_PATTERN)
    return isMatchesRegex(pattern)
}

fun String.isPhone(): Boolean {
    val pattern = Regex(PHONE_PATTERN)
    return isMatchesRegex(pattern)
}

fun String.isNumeric(): Boolean {
    val pattern = KexConstants.ONLY_DIGITS_PATTERN
    return isMatchesRegex(pattern)
}

fun String.isIPAddress(): Boolean {
    val pattern = IP_ADDRESS_PATTERN
    return isMatchesRegex(pattern)
}

fun String.isMatchesRegex(regex: String): Boolean {
    return regex.toRegex().matches(this)
}

fun String.isMatchesRegex(regex: Regex): Boolean {
    return regex.matches(this)
}

fun String.equalsIgnoreCase(other: String) = this.lowercase().contentEquals(other.lowercase())

fun String.removeSpaces(): String {
    return replace(" ", "")
}

fun String.removeLineBreaks(): String {
    return replace("\n", "").replace("\r", "")
}

inline fun String.ifNotEmpty(doFun: () -> Unit) {
    if (this.isNotEmpty()) {
        doFun()
    }
}

inline fun String.ifNotBlank(doFun: () -> Unit) {
    if (this.isNotBlank()) {
        doFun()
    }
}