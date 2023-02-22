package com.hoseus.logging

import com.hoseus.logging.mdc.MdcWriter

class DefaultLogMessageFactory(
	private val mdcWriter: MdcWriter
): LogMessageFactory, ErrorLogMessageFactory {

	/**
	 * The logName is part of the message, but is directly obtained from MDC in the log pattern.
	 */
	private fun registerLogNameInMDC(logName: String) {
		this.mdcWriter.put("logName", logName)
	}

	override fun start(logName: String, msg: String): String =
		this.registerLogNameInMDC(logName)
			.let { "Start. $msg" }

	override fun middle(logName: String, msg: String): String =
		this.registerLogNameInMDC(logName)
			.let { "Middle. $msg" }

	override fun end(logName: String, msg: String): String =
		this.registerLogNameInMDC(logName)
			.let { "End. $msg" }

	override fun error(logName: String, msg: String): String =
		this.registerLogNameInMDC(logName)
			.let { "Error. $msg" }
}
