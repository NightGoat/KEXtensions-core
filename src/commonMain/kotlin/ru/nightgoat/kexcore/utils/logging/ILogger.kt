package ru.nightgoat.kexcore.utils.logging

interface ILogger {
    fun loggE(message: String, tag: String?, e: Throwable? = null)
    fun loggD(message: String, tag: String?)
}