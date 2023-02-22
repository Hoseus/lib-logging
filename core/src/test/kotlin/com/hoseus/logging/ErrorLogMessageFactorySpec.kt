package com.hoseus.logging

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class ErrorLogMessageFactorySpec: BehaviorSpec() {
	init {
		Given("ErrorLogHelper") {
			data class Model(
				val attribute: String,
				@Sensitive val sensitiveAttribute: String
			)

			val errorLogMessageFactory: ErrorLogMessageFactory = DefaultLogMessageFactory(
				mdcWriter = { _, _ -> }
			)
			val logHelper: LogHelper = DefaultLogHelper()

			When("invoke logError") {
				val result = errorLogMessageFactory.error("logName", "message")
				Then("return log message") {
					result shouldBe "Error. message"
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
