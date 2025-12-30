plugins {
    alias(deps.plugins.android.library)
    alias(deps.plugins.jetbrains.compose)
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
                implementation(deps.jetbrains.compose.foundation)
                implementation(deps.jetbrains.compose.ui)
                implementation(deps.androidx.lifecycle.viewmodel)
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
    namespace = "dev.sergiobelda.fonament"
    compileSdk = deps.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = deps.versions.android.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    kotlin {
        jvmToolchain(17)
    }
}

tasks.withType<Jar> {
    from(file("$rootDir/${projects.fonamentPresentation.name}/samples/src/commonMain/kotlin"))
}

mavenPublishing {
    publishToMavenCentral(true)

    signAllPublications()
}
