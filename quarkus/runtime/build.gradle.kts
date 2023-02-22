import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("io.quarkus.extension")
    `java-library`
    `maven-publish`
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

repositories {
    mavenLocal()
    mavenCentral()
}

quarkusExtension {
    setDeploymentModule("lib-logging-quarkus-deployment")
}

evaluationDependsOn(":lib-logging-quarkus-deployment")

dependencies {
    implementation(platform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-core")
    kapt("io.quarkus:quarkus-extension-processor:${quarkusPlatformVersion}")

    api("com.hoseus:lib-logging:$version")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
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
