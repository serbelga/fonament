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
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(libs.androidx.lifecycle.viewmodel)
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
    namespace = "dev.sergiobelda.fonament"
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

tasks.withType<Jar> {
    from(file("$rootDir/${projects.fonamentPresentation.name}/samples/src/commonMain/kotlin"))
}

mavenPublishing {
    publishToMavenCentral(true)

    signAllPublications()
}
