package com.hoseus.logging.spring

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties

@AutoConfiguration
@EnableConfigurationProperties(LibLoggingConfigurationProperties::class)
class LibLoggingAutoConfiguration
