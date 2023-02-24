tasks.register("publishToMavenLocal") {
    dependsOn(gradle.includedBuilds.map { it.task(":publishToMavenLocal") })
}