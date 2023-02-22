package com.hoseus.logging.mdc

fun interface MdcWriter {
	/**
	 * Puts a value into the MDC context.
	 * @param key the indentification of the value.
	 * @param value the value.
	 */
	fun put(key: String, value: String)
}
