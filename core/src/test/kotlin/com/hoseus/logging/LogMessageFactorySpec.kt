package com.hoseus.logging

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class LogMessageFactorySpec: BehaviorSpec() {
	init {
		Given("LogHelper") {
			data class Model(
				val attribute: String,
				@Sensitive val sensitiveAttribute: String
			)

			val logMessageFactory: LogMessageFactory = DefaultLogMessageFactory(
				mdcWriter = { _, _ -> }
			)
			val logHelper: LogHelper = DefaultLogHelper()

			When("invoke start") {
				val result = logMessageFactory.start("logName", "message")
				Then("return log message") {
					result shouldBe "Start. message"
				}
			}
			When("invoke logMiddle") {
				val result = logMessageFactory.middle("logName", "message")
				Then("return log message") {
					result shouldBe "Middle. message"
				}
			}
			When("invoke logEnd") {
				val result = logMessageFactory.end("logName", "message")
				Then("return log message") {
					result shouldBe "End. message"
				}
			}
			When("invoke toJson on null object") {
				val result = logHelper.toJson(null)
				Then("return null string") {
					result shouldBe "null"
				}
			}

			When("invoke toJson on object") {
				val any = Model("value", "sensitive value")
				val result = logHelper.toJson(any)
				Then("return object as json") {
					result shouldBe """{"attribute":"value","sensitiveAttribute":"***"}"""
				}
			}
		}
	}
}
