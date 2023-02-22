import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.22"
    `java-library`
    `maven-publish`
}

group = "com.hoseus"
version = "0.1.0"

val log4j2Version by extra("2.19.0")
val jbossLoggingVersion by extra("3.5.0.Final")
val slf4jVersion by extra("2.0.6")
val jacksonVersion by extra("2.14.1")
val kotestVersion by extra("5.5.4")

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    compileOnly("org.apache.logging.log4j:log4j-api:${log4j2Version}")
    compileOnly("org.jboss.logging:jboss-logging:${jbossLoggingVersion}")
    compileOnly("org.slf4j:slf4j-api:${slf4jVersion}")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${jacksonVersion}")

    testImplementation("io.kotest:kotest-runner-junit5:${kotestVersion}")
    testImplementation("io.kotest:kotest-assertions-core:${kotestVersion}")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("project") {
            val isSnapshot = (project.properties["snapshot"] as String? ?: "false").toBoolean()

            artifactId = "${project.name}"
            version = if (isSnapshot) "${version}-SNAPSHOT" else "${version}"
            from(components["java"])
        }
    }
}
