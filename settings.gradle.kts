@file:Suppress("UnstableApiUsage")

import build.gradle.api.includeModule

pluginManagement {
    includeBuild("build-settings-logic")
    includeBuild("build-logic")
}

plugins {
    id("build-settings-default")
}

dependencyResolutionManagement {
    versionCatalogs {
        create("buildCatalog") {
            from(files("./gradle/catalogs/buildCatalog.versions.toml"))
        }
    }
}

rootProject.name = "nirmato-ai"

includeModule("nirmato-ai-core")

listOf("nirmato-model-ollama").forEach {

}

listOf("nirmato-store-chroma").forEach {
}
