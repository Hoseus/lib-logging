package com.hoseus.logging.spring

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.DefaultValue

@ConfigurationProperties(prefix = "hoseus.logging")
data class LibLoggingConfigurationProperties(
	/**
	 * Elk configurations.
	 */
	@DefaultValue
	val elk: ElkProperties
) {
	data class ElkProperties(
		/**
		 * Logstash configurations.
		 */
		@DefaultValue
		val logstash: LogstashProperties,

		@DefaultValue
		val additionalField: AdditionalFieldProperties,
	) {
		data class LogstashProperties(
			/**
			 * Enable sending logs to logstash.
			 */
			@DefaultValue("false")
			val enabled: Boolean,
			/**
			 * Logstash host.
			 */
			@DefaultValue("127.0.0.1")
			val host: String,
			/**
			 * Logstash port.
			 */
			@DefaultValue("12201")
			val port: Int,
		)

		data class AdditionalFieldProperties (
			/**
			 * Name of the application to send to logstash.
			 */
			@DefaultValue("\${spring.application.name}")
			val appName: String,
			/**
			 * Environment to send to logstash.
			 */
			@DefaultValue("\${spring.profiles.active}")
			val environment: String
		)
	}
}


