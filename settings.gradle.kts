pluginManagement {
    includeBuild("build-settings-logic")
    includeBuild("build-logic")
}

plugins {
    id("build-settings-default")
    id("build-foojay")
}

rootProject.name = "nirmato-ai"

include("modules:core")

include("publishing:bom")
include("publishing:version-catalog")

include("models:model-ollama")

include("stores:store-chroma")
