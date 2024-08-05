@file:Suppress("UnstableApiUsage")

import build.gradle.api.includeModule

pluginManagement {
    includeBuild("build-settings-logic")
    includeBuild("build-logic")
}

plugins {
    id("build-settings-default")
}

rootProject.name = "nirmato-ai"

includeModule("nirmato-ai-core")

fun includeAll(module: String) {
    include(module)

    val name = module.replace(":", "/")
    file("$rootDir/$name").listFiles().forEach {
        include("$module:${it.name}")
    }
}

includeAll("adapters:models")
includeAll("adapters:vector-stores")
