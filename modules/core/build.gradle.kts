import org.jetbrains.dokka.DokkaConfiguration.Visibility
import org.jetbrains.dokka.gradle.DokkaTaskPartial

plugins {
    alias(libraries.plugins.dokka.gradle.plugin)
    alias(libraries.plugins.kotlinx.kover)

    id("build-multiplatform")
    id("build-project-default")
    id("build-publishing")
}

kotlin {
    explicitApi()

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.contracts.ExperimentalContracts")
                optIn("kotlin.RequiresOptIn")
                optIn("kotlin.time.ExperimentalTime")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                optIn("kotlinx.serialization.ExperimentalSerializationApi")
            }
        }

        matching { it.name.endsWith("Test") }.configureEach {
            languageSettings.apply {
                optIn("kotlinx.coroutines.FlowPreview")
            }
        }

        val commonMain by getting {
            kotlin {
                srcDirs("src/commonMain/kotlinX")
            }
            dependencies {
                implementation(libraries.kotlinx.coroutines.core)
                implementation(libraries.kotlinx.serialization.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libraries.kotlin.test)
                implementation(libraries.kotlinx.coroutines.test)
            }
        }
    }
}

tasks {
    withType<DokkaTaskPartial>().configureEach {
        dokkaSourceSets.configureEach {
            documentedVisibilities.set(Visibility.values().toSet())
        }
        failOnWarning.set(true)
        offlineMode.set(true)
    }
}
