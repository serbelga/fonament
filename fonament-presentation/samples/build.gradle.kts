import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(deps.plugins.android.kotlinMultiplatformLibrary)
    alias(deps.plugins.jetbrains.compose)
    alias(deps.plugins.jetbrains.kotlin.composeCompiler)
    alias(deps.plugins.jetbrains.kotlin.multiplatform)
    alias(deps.plugins.sergiobelda.convention.spotless)
}

kotlin {
    androidLibrary {
        namespace = "dev.sergiobelda.fonament.samples"
        compileSdk = deps.versions.android.compileSdk.get().toInt()
        minSdk = deps.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.fonamentPresentation)

            implementation(deps.jetbrains.androidx.lifecycle.viewmodelCompose)
            implementation(deps.jetbrains.compose.foundation)
            implementation(deps.jetbrains.compose.material3)
            implementation(deps.jetbrains.compose.materialIconsExtended)
            implementation(deps.jetbrains.compose.ui)
            implementation(deps.jetbrains.compose.uiToolingPreview)
            implementation(deps.jetbrains.kotlinx.collections.immutable)
        }
        androidMain.dependencies {
            implementation(deps.androidx.core.ktx)
        }
    }
}
