package io.github.nightgoat.kexcore.utils.logging

object ConsoleLogger : ILogger {
    override fun loggE(message: String, tag: String?, e: Throwable?) {
        println("$tag: $message")
        e?.printStackTrace()
    }

    override fun loggD(message: String, tag: String?) {
        println("$tag: $message")
    }
}