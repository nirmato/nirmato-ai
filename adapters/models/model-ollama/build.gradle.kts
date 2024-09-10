@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.config.ApiVersion
import org.jetbrains.kotlin.config.LanguageVersion
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import build.gradle.dsl.withCompilerArguments
import org.jetbrains.dokka.DokkaConfiguration.Visibility
import org.jetbrains.dokka.gradle.DokkaTaskPartial
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnLockMismatchReport
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.yarn

plugins {
    id(libraries.plugins.kotlin.multiplatform.get().pluginId)
    alias(libraries.plugins.kotlinx.kover)

    id("build-project-default")
    id("build-publishing")
}

kotlin {
    explicitApi()

    targets.all {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    withCompilerArguments {
                        requiresOptIn()
                        suppressExpectActualClasses()
                        suppressVersionWarnings()
                    }
                }
            }
        }
    }

    jvm {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    withCompilerArguments {
                        requiresJsr305()
                    }
                }
            }
        }
    }

    js {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    sourceMap = true
                }
            }
        }

        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                    useConfigDirectory(project.projectDir.resolve("karma.config.d").resolve("js"))
                }
            }
        }
    }

    sourceSets {
        all {
            languageSettings.apply {
                apiVersion = ApiVersion.KOTLIN_2_0.toString()
                languageVersion = LanguageVersion.KOTLIN_2_0.toString()
                progressiveMode = true

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

                implementation(project(":core"))
                implementation("org.nirmato.ollama:nirmato-ollama-client-ktor:unspecified")
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

plugins.withType<YarnPlugin> {
    yarn.apply {
        lockFileDirectory = rootDir.resolve("gradle/js")
        yarnLockMismatchReport = YarnLockMismatchReport.FAIL
        yarnLockAutoReplace = true
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

publishing {
    publications.configureEach {
        with(this as MavenPublication) {
            artifactId = "${rootProject.name}-${project.name}-$name"
        }
    }
}
