package com.hoseus.logging

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.hoseus.logging.mask.MaskModule

class DefaultLogHelper(
	private val mapper: ObjectMapper = jacksonObjectMapper().registerModule(MaskModule()),
): LogHelper {
	override fun toJson(any: Any?): String = this.mapper.writeValueAsString(any)
}
