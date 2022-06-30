package ru.nightgoat.kexcore

import ru.nightgoat.kexcore.utils.Kex
import kotlin.math.abs

fun Int?.orZero() = this ?: 0

fun Int.toNegative() = -abs(this)
fun Int.toPositive() = abs(this)

fun Int.reverse() = this.unaryMinus()

fun Int.takeIfZero() = this.takeIf { it == 0 }
fun Int.takeIfNotZero() = this.takeIf { it != 0 }

fun Int?.toStringOrEmpty() = this?.toString().orEmpty()
fun Int.takeIfZeroOrEmpty() = this.takeIf { it == 0 }?.toStringOrEmpty()

fun Int.takeIfNotNegative() = this.takeIf { it >= 0 }

fun Int?.isTrue(trueInt: Int = 1) = this == trueInt
fun Int?.isFalse(falseInt: Int = 0) = this == falseInt

fun Int?.toBooleanOrNull(trueInt: Int = 1, falseInt: Int = 0): Boolean? {
    return when (this) {
        trueInt -> true
        falseInt -> false
        else -> null
    }
}

fun Int?.divideSafe(divideBy: Int) = if (this != null && divideBy != 0) {
    this / divideBy
} else {
    Kex.loggE("divideSafe(): division by zero!")
    null
}

fun Int?.divideSafeOrZero(divideBy: Int) = if (this != null && divideBy != 0) {
    this / divideBy
} else {
    Kex.loggE("divideSafe(): division by zero!")
    0
}

fun Int.convertSecondsToMilliseconds(): Long = this * 1000L

fun Int?.toDoubleOrZero() = this?.toDouble().orZero()

fun Int?.toLongOrZero() = this?.toLong().orZero()
