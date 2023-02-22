package com.hoseus.logging

/**
 * Factory that provides template methods for log messages.
 */
interface LogMessageFactory {
	/**
	 * Provides a message using a template with the format:
	 * 'Start. <msg>'.
	 * Should be used to notify that a method or process is starting.
	 *
	 * @param logName An identifier for this log message, that can be later
	 * used to find this particular log.
	 * @param msg The content of the log. For example a received request or
	 * the received arguments of a method.
	 * @return The log message.
	 */
	fun start(logName: String, msg: String): String

	/**
	 * Provides a message using a template with the format:
	 * 'Middle. <msg>'.
	 * Should be used in between the Start and End messages.
	 *
	 * @param logName An identifier for this log message, that can be later
	 * used to find this particular log.
	 * @param msg The content of the log. For example a received response or
	 * the result of a process.
	 * @return The log message.
	 */
	fun middle(logName: String, msg: String): String

	/**
	 * Provides a message using a template with the format:
	 * 'End. <msg>'.
	 * Should be used to notify that a method or process ended.
	 *
	 * @param logName An identifier for this log message, that can be later
	 * used to find this particular log.
	 * @param msg The content of the log. For example a received response or
	 * the result of a process.
	 * @return The log message.
	 */
	fun end(logName: String, msg: String): String
}
