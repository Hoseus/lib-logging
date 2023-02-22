import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("kapt")
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

dependencies {
    implementation(platform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation(project(":lib-logging-quarkus"))
    implementation("io.quarkus:quarkus-core-deployment")
    kapt("io.quarkus:quarkus-extension-processor:${quarkusPlatformVersion}")

    testImplementation("io.quarkus:quarkus-junit5-internal")
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
