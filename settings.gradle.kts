@file:Suppress("UnstableApiUsage")

import org.gradle.api.initialization.includeModule

pluginManagement {
    includeBuild("build-settings-logic")
    includeBuild("build-logic")
}

plugins {
    id("build-settings-default")
}

rootProject.name = "nirmato-ai"

includeModule("core")
includeModule("bom")
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
