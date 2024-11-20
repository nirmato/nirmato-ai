import org.gradle.api.initialization.includeModule
import org.gradle.api.initialization.includeSample
import org.gradle.api.provider.gradleBooleanProperty

pluginManagement {
    includeBuild("build-settings-logic")
    includeBuild("build-logic")
}

plugins {
    id("build-settings-default")
    id("build-foojay")
}

rootProject.name = "nirmato-ai"

includeModule("core")
includeModule("platform")
includeModule("version-catalog")

fun includeAll(module: String) {
    include(module)

    val name = module.replace(":", "/")
    file("$rootDir/$name").listFiles().forEach {
        include("$module:${it.name}")
    }
}

includeAll("adapters:models")
includeAll("adapters:vector-stores")
