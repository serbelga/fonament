plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.composeMultiplatform)
    alias(libs.plugins.jetbrains.dokka)
    alias(libs.plugins.jetbrains.kotlin.composeCompiler)
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.vanniktech.mavenpublish)
    alias(libs.plugins.sergiobelda.convention.spotless)
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
                api(projects.fonamentPresentation)

                api(project.dependencies.platform(libs.koin.bom))
                api(libs.koin.core)
                api(libs.koin.composeViewmodel)

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

mavenPublishing {
    coordinates(
        artifactId = "fonament-di-koin",
    )

    publishToMavenCentral(true)

    signAllPublications()
}
