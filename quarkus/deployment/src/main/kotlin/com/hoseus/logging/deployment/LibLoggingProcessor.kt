package com.hoseus.logging.deployment

import io.quarkus.deployment.annotations.BuildStep
import io.quarkus.deployment.builditem.FeatureBuildItem
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourceBuildItem

class LibLoggingProcessor {
	@BuildStep
	fun feature(): FeatureBuildItem {
		return FeatureBuildItem(FEATURE)
	}

	@BuildStep
	fun nativeImageResource(): NativeImageResourceBuildItem {
		return NativeImageResourceBuildItem("default-logstash-fields.properties")
	}

	companion object {
		private const val FEATURE = "lib-logging-quarkus"
	}
}
