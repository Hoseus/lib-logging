package com.hoseus.logging

/**
 * Used to provide a message to the logger.
 */
fun interface LogMessageProvider {
	/**
	 * @param logMessageFactory A [LogMessageFactory] that can be used for generating the message.
	 * @param logHelper A [LogHelper] that provides helper methods for building the message.
	 * @return The log message.
	 */
	fun get(logMessageFactory: LogMessageFactory, logHelper: LogHelper): String
}
