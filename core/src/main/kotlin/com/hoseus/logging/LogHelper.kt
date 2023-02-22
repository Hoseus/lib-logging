package com.hoseus.logging

/**
 * Helper that provides utilities for creating log messages.
 */
interface LogHelper {
	/**
	 * Serializes an object to json.
	 * @param any the object to serialize.
	 * @return The object serialized to json.
	 */
	fun toJson(any: Any?): String
}
