plugins {
    alias(deps.plugins.android.library)
    alias(deps.plugins.jetbrains.composeMultiplatform)
    alias(deps.plugins.jetbrains.dokka)
    alias(deps.plugins.jetbrains.kotlin.composeCompiler)
    alias(deps.plugins.jetbrains.kotlin.multiplatform)
    alias(deps.plugins.vanniktech.mavenpublish)
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
                api(projects.fonamentPresentation)

                api(project.dependencies.platform(deps.koin.bom))
                api(deps.koin.core)
                api(deps.koin.composeViewmodel)

                implementation(compose.ui)
                implementation(deps.jetbrains.androidx.lifecycle.viewmodelCompose)
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
    namespace = "dev.sergiobelda.fonament.di.koin"
    compileSdk = deps.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        minSdk = deps.versions.androidMinSdk.get().toInt()

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
