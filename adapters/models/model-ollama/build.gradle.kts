plugins {
    alias(libraries.plugins.kotlinx.kover)

    `maven-publish`

    id("build-kotlin-multiplatform")
    id("build-project-default")
    id("build-maven-publishing-configurer")
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
                api(project(":core"))
                api(libraries.kotlinx.coroutines.core)
                api(libraries.nirmato.ollama.client)

                implementation(libraries.nirmato.ollama.client.ktor)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libraries.kotlin.test)
                implementation(libraries.kotlinx.coroutines.test)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(libraries.ktor.client.cio)
            }
        }
    }
}
