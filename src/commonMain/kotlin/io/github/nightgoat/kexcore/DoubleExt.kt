package io.github.nightgoat.kexcore

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.round

/**
 * Returns string from double without zeroes at the end
 */

fun Double?.orZero(): Double {
    return this ?: 0.0
}

fun Double?.toIntOrZero(): Int {
    return this?.toInt().orZero()
}

fun Double?.toLongOrZero(): Long {
    return this?.toLong().orZero()
}

fun Double.roundTo(numberOfZeroes: Int): Double {
    val divider = 10.0
    val dividerWithPow = divider.pow(numberOfZeroes)
    return round((this) * dividerWithPow) / dividerWithPow
}

fun Double.toNegative() = -abs(this)
fun Double.toPositive() = abs(this)

fun Double.reverse() = this.unaryMinus()

fun Double.takeIfZero() = this.takeIf { it == 0.0 }
fun Double.takeIfNotZero() = this.takeIf { it != 0.0 }
fun Double.takeIfNotNegative() = this.takeIf { it >= 0.0 }