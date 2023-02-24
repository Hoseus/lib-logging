package com.hoseus.logging.runtime

import io.quarkus.arc.Unremovable
import io.quarkus.runtime.annotations.ConfigGroup
import io.quarkus.runtime.annotations.ConfigPhase
import io.quarkus.runtime.annotations.ConfigRoot
import io.smallrye.config.ConfigMapping
import io.smallrye.config.WithDefault

/**
 * Lib logging configuration properties
 */
@Unremovable
@ConfigMapping(prefix = "hoseus.logging")
@ConfigRoot(phase = ConfigPhase.RUN_TIME)
interface LibLoggingRuntimeConfiguration {
	/**
	 * Elk configurations.
	 */
	fun elk(): ElkProperties

	@ConfigGroup
	interface ElkProperties {
		/**
		 * Logstash configurations.
		 */
		fun logstash(): LogstashProperties

		/**
		 * Additional fields for elk indexes.
		 */
		fun additionalField(): AdditionalFieldProperties

		@ConfigGroup
		interface LogstashProperties {
			/**
			 * Enable sending logs to logstash.
			 */
			@WithDefault("false")
			fun enabled(): Boolean
			/**
			 * Logstash host.
			 */
			@WithDefault("127.0.0.1")
			fun host(): String
			/**
			 * Logstash port.
			 */
			@WithDefault("12201")
			fun port(): Int
		}

		@ConfigGroup
		interface AdditionalFieldProperties {
			/**
			 * Name of the application to send to logstash.
			 */
			@WithDefault("\${quarkus.application.name}")
			fun appName(): String
			/**
			 * Environment to send to logstash.
			 */
			@WithDefault("\${quarkus.profile}")
			fun environment(): String
		}
	}
}
