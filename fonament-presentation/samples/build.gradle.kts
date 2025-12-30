plugins {
    alias(deps.plugins.jetbrains.kotlin.multiplatform)
    alias(deps.plugins.android.library)
    alias(deps.plugins.jetbrains.compose)
    alias(deps.plugins.jetbrains.kotlin.composeCompiler)
    alias(deps.plugins.sergiobelda.convention.spotless)
}

kotlin {
    androidTarget()
    jvm("desktop")
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.fonamentPresentation)

                implementation(deps.jetbrains.androidx.lifecycle.viewmodelCompose)
                implementation(deps.jetbrains.compose.foundation)
                implementation(deps.jetbrains.compose.material3)
                implementation(deps.jetbrains.compose.materialIconsExtended)
                implementation(deps.jetbrains.compose.ui)
                implementation(deps.jetbrains.kotlinx.collections.immutable)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(deps.androidx.core.ktx)
            }
        }
    }
}

android {
    namespace = "dev.sergiobelda.fonament.samples"
    compileSdk = deps.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = deps.versions.android.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    kotlin {
        jvmToolchain(17)
    }

    dependencies {
        implementation(deps.jetbrains.compose.uiToolingPreview)
        debugImplementation(deps.jetbrains.compose.uiTooling)
    }
}
