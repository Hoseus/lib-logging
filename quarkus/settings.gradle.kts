pluginManagement {
    val quarkusExtensionPluginVersion: String by settings
    val quarkusExtensionPluginId: String by settings
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        kotlin("jvm") version "1.7.22"
        kotlin("kapt") version "1.7.22"
        id(quarkusExtensionPluginId) version quarkusExtensionPluginVersion
    }
}

rootProject.name = "lib-logging-quarkus-parent"

include("runtime", "deployment")

project( ":runtime" ).name = "lib-logging-quarkus"
project( ":deployment" ).name = "lib-logging-quarkus-deployment"

