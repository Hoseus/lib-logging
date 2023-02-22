package com.hoseus.logging.mask

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.BeanDescription
import com.fasterxml.jackson.databind.SerializationConfig
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.hoseus.logging.Sensitive

class MaskSerializer: StdSerializer<Any>(Any::class.java) {
	override fun serialize(value: Any?, gen: JsonGenerator?, provider: SerializerProvider?) {
		gen?.writeString("***")
	}
}

class MaskBeanSerializerModifier : BeanSerializerModifier() {
	override fun changeProperties(
		config: SerializationConfig?,
		beanDesc: BeanDescription?,
		beanProperties: MutableList<BeanPropertyWriter>?
	): MutableList<BeanPropertyWriter> {
		beanProperties?.filter {
			it.getAnnotation(Sensitive::class.java) != null
		}?.forEach {
			it.assignSerializer(MaskSerializer())
		}

		return super.changeProperties(config, beanDesc, beanProperties)
	}
}

class MaskModule : SimpleModule(MaskModule::class.java.name) {
	override fun setupModule(context: SetupContext) {
		super.setupModule(context)
		context.addBeanSerializerModifier(MaskBeanSerializerModifier())
	}
}

