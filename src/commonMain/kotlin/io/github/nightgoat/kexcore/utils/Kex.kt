package io.github.nightgoat.kexcore.utils

import io.github.nightgoat.kexcore.utils.logging.ConsoleLogger
import io.github.nightgoat.kexcore.utils.logging.ILogger
import kotlin.native.concurrent.ThreadLocal

/**
 * Main utils class
 * @author NightGoat
 * */
@ThreadLocal
object Kex {
    private var logger: ILogger = ConsoleLogger
    private var isLoggerOn = true
    private var isStackTraceOn: Boolean = true

    fun setCustomLogger(newLogger: ILogger): Kex {
        logger = newLogger
        return this
    }

    fun turnOffStackTrace(): Kex {
        isStackTraceOn = false
        return this
    }

    fun turnOnStackTrace(): Kex {
        isStackTraceOn = true
        return this
    }

    /**
     * Turns off this noisy logger.
     * "Will you shut up and listen to me? Shut down all garbage mashers on the Detention Level, will you? Do you copy?"
     * */
    fun turnOffLogger(): Kex {
        isLoggerOn = false
        return this
    }

    fun loggE(message: String, tag: String? = null, e: Throwable? = null) {
        if (isLoggerOn) {
            val throwable = e.takeIf { isStackTraceOn }
            logger.loggE(message, tag, throwable)
        }
    }

    fun loggD(message: String, tag: String? = null) {
        if (isLoggerOn) {
            logger.loggD(message, tag)
        }
    }
}