package com.hoseus.logging

/**
 * Used to provide a message to the logger.
 */
fun interface ErrorLogMessageProvider {
	/**
	 * @param logMessageFactory An [ErrorLogMessageFactory] that can be used for generating the message.
	 * @param logHelper A [LogHelper] that provides helper methods for building the message.
	 * @return The log message.
	 */
	fun get(logMessageFactory: ErrorLogMessageFactory, logHelper: LogHelper): String
}
