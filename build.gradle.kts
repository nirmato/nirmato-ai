plugins {
    alias(libraries.plugins.kotlinx.serialization) apply false
    alias(libraries.plugins.kotlinx.bcv)
    alias(libraries.plugins.detekt)

    id("build-project-default")
    id("build-wrapper-configurer")
    id("build-detekt-configurer")
}

description = "Root Project"

allprojects {
    group = "org.nirmato.ai"

    configurations.all {
        resolutionStrategy {
            failOnNonReproducibleResolution()
        }
    }
}

apiValidation {
    ignoredPackages.add("org.nirmato.ai.internal")

    ignoredProjects.addAll(
        listOf(
            "platform",
            "version-catalog",
        )
    )
}

tasks {
    // Fix CodeQL workflow execution
    val testClasses by registering
}
