package com.hoseus.logging

/**
 * Factory that provides template methods for error log messages.
 */
interface ErrorLogMessageFactory {
	/**
	 * Provides an error message using a template with the format:
	 * 'Error. <msg>'
	 *
	 * @param logName An identifier for this log message, that can be later
	 * used to find this particular log.
	 * @param msg The content of the log. For example an exception message or
	 * the result of a bad request response.
	 * @return The error log message.
	 */
	fun error(logName: String, msg: String): String
}
