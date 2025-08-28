plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.compose.multiplatform)
    alias(libs.plugins.jetbrains.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    id("dev.sergiobelda.gradle.spotless")
}

group = "dev.sergiobelda.fonament.di.koin"
version = libs.versions.fonament.get()

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
                api(projects.fonament)

                api(project.dependencies.platform(libs.koin.bom))
                api(libs.koin.core)
                api(libs.koin.compose.viewmodel)

                implementation(compose.ui)
                implementation(libs.jetbrains.androidx.lifecycle.viewmodelCompose)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.core.ktx)
            }
        }
    }
}

android {
    namespace = "dev.sergiobelda.fonament.di.koin"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    kotlin {
        jvmToolchain(17)
    }
}
