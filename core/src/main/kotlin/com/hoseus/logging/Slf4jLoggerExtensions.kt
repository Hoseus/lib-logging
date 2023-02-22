package com.hoseus.logging

import org.slf4j.Logger
import org.slf4j.MDC

private val logMessageFactory = DefaultLogMessageFactory(
	mdcWriter = { key, value -> MDC.put(key, value) }
)

private val logHelper = DefaultLogHelper()

/**
 * Log a message by passing a [LogMessageProvider].
 * @param e The exception which stacktrace you want to log.
 * @param logMessageProvider A [LogMessageProvider] that returns the message to log.
 */
fun Logger.infoExtended(e: Throwable? = null, logMessageProvider: LogMessageProvider) =
	if(e != null) {
		info(logMessageProvider.get(logMessageFactory, logHelper), e)
	} else {
		info(logMessageProvider.get(logMessageFactory, logHelper))
	}

/**
 * Log a message by passing a [LogMessageProvider].
 * @param e The exception which stacktrace you want to log.
 * @param logMessageProvider A [LogMessageProvider] that returns the message to log.
 */
fun Logger.warnExtended(e: Throwable? = null, logMessageProvider: LogMessageProvider) =
	if(e != null) {
		warn(logMessageProvider.get(logMessageFactory, logHelper), e)
	} else {
		warn(logMessageProvider.get(logMessageFactory, logHelper))
	}

/**
 * Log a message by passing a [LogMessageProvider].
 * @param e The exception which stacktrace you want to log.
 * @param logMessageProvider A [LogMessageProvider] that returns the message to log.
 */
fun Logger.traceExtended(e: Throwable? = null, logMessageProvider: LogMessageProvider) =
	if(e != null) {
		trace(logMessageProvider.get(logMessageFactory, logHelper), e)
	} else {
		trace(logMessageProvider.get(logMessageFactory, logHelper))
	}

/**
 * Log a message by passing a [LogMessageProvider].
 * @param e The exception which stacktrace you want to log.
 * @param logMessageProvider A [LogMessageProvider] that returns the message to log.
 */
fun Logger.debugExtended(e: Throwable? = null, logMessageProvider: LogMessageProvider) =
	if(e != null) {
		debug(logMessageProvider.get(logMessageFactory, logHelper), e)
	} else {
		debug(logMessageProvider.get(logMessageFactory, logHelper))
	}

/**
 * Log an error message by passing a [LogMessageProvider].
 * @param e The exception which stacktrace you want to log.
 * @param messageProvider A [ErrorLogMessageProvider] that returns the error message to log.
 */
fun Logger.errorExtended(e: Throwable? = null, messageProvider: ErrorLogMessageProvider) =
	if(e != null) {
		error(messageProvider.get(logMessageFactory, logHelper), e)
	} else {
		error(messageProvider.get(logMessageFactory, logHelper))
	}
