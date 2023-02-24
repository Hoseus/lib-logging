# lib-logging

### Description

Library that provides my preferred log principles. Extends log4j2 and other libraries with methods that provide log
messages for different situations. The spring and quarkus versions of this library also include a default log pattern
and integration with elk.

### Dependencies

You can use any of the implementations presented here:

* core
```gradle
implementation("com.hoseus:lib-logging:0.1.0")
```

* spring
```gradle
implementation("org.springframework.boot:spring-boot-starter-log4j2")
implementation("org.apache.logging.log4j:log4j-api-kotlin:${log4jApiKotlinVersion}")

implementation("biz.paluch.logging:logstash-gelf:${logstashGelfVersion}") //Only if using elk

implementation("com.hoseus:lib-logging-spring:0.1.0")
```

* quarkus
```gradle
implementation("io.quarkus:quarkus-logging-gelf") //Only if using elk

implementation("com.hoseus:lib-logging-quarkus:0.1.0")
```

### Extensions

The extensions provided for the most common logging frameworks. This feature is included
in all options of this library:

1. Log info
   ```kotlin
   //Logging without using the logMessageFactory
   logger.infoExtended{_, _ -> "msg"}
   logger.infoExtended(RuntimeException("Error!")){_, _ -> "msg"}
   //Logging at the start of a method or process using the logMessageFactory
   logger.infoExtended{factory, _ -> factory.start("Log identification", "msg")}
   logger.infoExtended(RuntimeException("Error!")){factory, _ -> factory.start("Log identification", "msg")}
   //Logging in between the start and the end of a method or process using logMessageFactory
   logger.infoExtended{factory, _ -> factory.middle("Log identification", "msg")}
   logger.infoExtended(RuntimeException("Error!")){factory, _ -> factory.middle("Log identification", "msg")}
   //Logging at the end of a method or process using logMessageFactory
   logger.infoExtended{factory, _ -> factory.end("Log identification", "msg")}
   logger.infoExtended(RuntimeException("Error!")){factory, _ -> factory.end("Log identification", "msg")}
   ```
2. Log warn
   ```kotlin
   //Logging without using the logMessageFactory
   logger.warnExtended{"msg"}
   logger.warnExtended(RuntimeException("Error!")){"msg"}
   //Logging at the start of a method or process using logMessageFactory
   logger.warnExtended{factory, _ -> factory.start("Log identification", "msg")}
   logger.warnExtended(RuntimeException("Error!")){factory, _ -> factory.start("Log identification", "msg")}
   //Logging in between the start and the end of a method or process using logMessageFactory
   logger.warnExtended{factory, _ -> factory.middle("Log identification", "msg")}
   logger.warnExtended(RuntimeException("Error!")){factory, _ -> factory.middle("Log identification", "msg")}
   //Logging at the end of a method or process using logMessageFactory
   logger.warnExtended{factory, _ -> factory.end("Log identification", "msg")}
   logger.warnExtended(RuntimeException("Error!")){factory, _ -> factory.end("Log identification", "msg")}
   ```
3. Log debug
   ```kotlin
   //Logging without using the logMessageFactory
   logger.debugExtended{"msg"}
   logger.debugExtended(RuntimeException("Error!")){"msg"}
   //Logging at the start of a method or process using logMessageFactory
   logger.debugExtended{factory, _ -> factory.start("Log identification", "msg")}
   logger.debugExtended(RuntimeException("Error!")){factory, _ -> factory.start("Log identification", "msg")}
   //Logging in between the start and the end of a method or process using logMessageFactory
   logger.debugExtended{factory, _ -> factory.middle("Log identification", "msg")}
   logger.debugExtended(RuntimeException("Error!")){factory, _ -> factory.middle("Log identification", "msg")}
   //Logging at the end of a method or process using logMessageFactory
   logger.debugExtended{factory, _ -> factory.end("Log identification", "msg")}
   logger.debugExtended(RuntimeException("Error!")){factory, _ -> factory.end("Log identification", "msg")}
   ```
4. Log trace
   ```kotlin
   //Logging without using the logMessageFactory
   logger.traceExtended{"msg"}
   logger.traceExtended(RuntimeException("Error!")){"msg"}
   //Logging at the start of a method or process using logMessageFactory
   logger.traceExtended{factory, _ -> factory.start("Log identification", "msg")}
   logger.traceExtended(RuntimeException("Error!")){factory, _ -> factory.start("Log identification", "msg")}
   //Logging in between the start and the end of a method or process using logMessageFactory
   logger.traceExtended{factory, _ -> factory.middle("Log identification", "msg")}
   logger.traceExtended(RuntimeException("Error!")){factory, _ -> factory.middle("Log identification", "msg")}
   //Logging at the end of a method or process using logMessageFactory
   logger.traceExtended{factory, _ -> factory.end("Log identification", "msg")}
   logger.traceExtended(RuntimeException("Error!")){factory, _ -> factory.end("Log identification", "msg")}
   ```
5. Log error
   ```kotlin
   //Logging without using the logMessageFactory
   logger.errorExtended{"errorMsg"}
   logger.errorExtended(RuntimeException("Error!")){"errorMsg"}
   //Logging error using logMessageFactory
   logger.errorExtended{factory, _ -> factory.error("Log identification", "errorMsg")}
   logger.errorExtended(RuntimeException("Error!")){factory, _ -> factory.error("Log identification", "errorMsg")}
   ```

6. Log an object as json. Notice the use of @Sensitive, this will hide the information of this field in the logs and
   protect sensitive information.
   ```kotlin
   //Logging error using logMessageFactory
   data class User {
      val mail: String,
      @Sensitive val password: String
   }

   fun saveUser(user: User) {
        logger.infoExtended{factory, helper -> factory.start("saveUser", "User to save: ${helper.toJson(user)}")}
   }
   ```

### Examples

```kotlin
package com.hoseus

import org.apache.logging.log4j.LogManager

data class Coordinate(
    val x: Int,
    val y: Int
)

class MyClass {
   fun sum(a: Int, b: Int): Int {
      logger.infoExtended{ factory, _ -> factory.start("sum", "a: $a. b: $b") }
      logger.infoExtended{ factory, _ -> factory.middle("sum", "Doing sum") }
      val result = a + b
      logger.infoExtended{ factory, _ -> factory.end("sum", "result: $result") }
      return result
   }

   fun div(a: Int, b: Int): Int {
      try {
         logger.infoExtended{ factory, _ -> factory.start("div", "a: $a. b: $b") }
         logger.infoExtended{ factory, _ -> factory.middle("div", "Doing div") }
         val result = a / b
         logger.infoExtended{ factory, _ -> factory.end("div", "result: $result") }
         return result
      } catch(e: ArithmeticException) {
         logger.errorExtended { factory, _ -> factory.error("div by zero", "tried to divide by zero", e) }
	  }
   }

   fun toCoordinate(x: Int, y: Int): Coordinate {
      logger.infoExtended{ factory, _ -> factory.start("toCoordinate", "x: $x. y: $y") }
      val coordinate = Coordinate(x, y)
      logger.infoExtended{ factory, helper -> factory.end("toCoordinate", "coordinate: ${helper.toJson(coordinate)}") }
      return coordinate
   }

   companion object {
      private val logger = LogManager.getLogger(com.hoseus.MyClass::class.java)
   }
}
```

### Default log pattern:
Spring
```xml
<Console name="Console" target="SYSTEM_OUT">
  <PatternLayout
    pattern="%highlight{[%-5level]} [%d{yyyy-MM-dd HH:mm:ss.SSS}] %style{[%mdc{traceId}]}{blue} %style{[%t]}{yellow} %style{[%c]}{cyan} %style{[%mdc{logName}]}{magenta} - %highlight{%msg%n%throwable%n}"/>
</Console>
```

Quarkus
```properties
quarkus.log.console.format=[%-5p] [%d{yyyy-MM-dd HH:mm:ss.SSS}] [%X{traceId}] [%t] [%c] [%X{logName}] - %s%n%e%n
```

### Configuration properties
They are the same for both spring and quarkus.

| Name                                                 | Description                                            | Spring                              | Quarkus                              |
|------------------------------------------------------|--------------------------------------------------------|-------------------------------------|--------------------------------------|
| hoseus.logging.elk.logstash.enabled                  | If sending logs to logstash in gelf format is enabled. | Default: false                      | Default: false                       |
| hoseus.logging.elk.logstash.host                     | Logstash host.                                         | Default: 127.0.0.1                  | Default: 127.0.0.1                   |
| hoseus.logging.elk.logstash.port                     | Logstash port.                                         | Default: 12201                      | Default: 12201                       |
| hoseus.logging.elk.additional-properties.app-name    | The app name to send to elk as a field.                | Default: ${spring.application.name} | Default: ${quarkus.application.name} |
| hoseus.logging.elk.additional-properties.environment | The environment to send to elk as a field.             | Default: ${spring.profiles.active}  | Default: ${quarkus.profile}          |




### Release and versioning:

I used [uplift](https://upliftci.dev/)

#### 1) Release
```shell
uplift release
```
