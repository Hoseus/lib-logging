plugins {
    kotlin("jvm") apply false
    kotlin("kapt") apply false
    `java-library`
    `maven-publish`
}

subprojects {
    group = "com.hoseus"
    version = "0.1.0"
}
