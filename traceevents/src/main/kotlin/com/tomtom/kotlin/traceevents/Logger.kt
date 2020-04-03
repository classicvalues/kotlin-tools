package com.tomtom.kotlin.traceevents

import java.time.LocalDateTime

interface Log {

    enum class Level { VERBOSE, DEBUG, INFO, WARN, ERROR }

    /**
     * Implement this interface for your own logger and set [theLogger] accordingly.
     */
    interface Logger {
        fun log(level: String, tag: String?, message: String, e: Throwable? = null)
    }


    companion object {
        /**
         * Override this function if you need to use a different logger than the default
         * implementation, which uses [println].
         */
        var theLogger: Logger = PrinLnLogger()

        /**
         * This method is called in this library only.
         */
        internal fun log(level: Level, tag: String?, message: String, e: Throwable? = null) = when (level) {
            Level.VERBOSE -> theLogger.log("VERBOSE", tag, message, e)
            Level.DEBUG -> theLogger.log("DEBUG", tag, message, e)
            Level.INFO -> theLogger.log("INFO", tag, message, e)
            Level.WARN -> theLogger.log("WARN", tag, message, e)
            Level.ERROR -> theLogger.log("ERROR", tag, message, e)
        }

        /**
         * Default implementation for [theLogger].
         */
        private class PrinLnLogger : Logger {
            override fun log(level: String, tag: String?, message: String, e: Throwable?) {
                val exceptionMsg = e?.message?.let { ", $it" } ?: ""
                println("${LocalDateTime.now()}: [$level] $tag: $message$exceptionMsg}")
            }
        }
    }
}
