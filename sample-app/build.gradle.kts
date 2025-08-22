plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.compose)
    id("dev.sergiobelda.gradle.spotless")
}

android {
    namespace = "dev.sergiobelda.fonament"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        applicationId = "dev.sergiobelda.fonament.sample.app"
        minSdk = libs.versions.androidMinSdk.get().toInt()
        targetSdk = libs.versions.androidTargetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kotlin {
        jvmToolchain(17)

        // TODO: Remove
        compilerOptions {
            freeCompilerArgs.add("-Xcontext-parameters")
        }
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.fonament)
    implementation(projects.fonament.samples)
    implementation(projects.fonamentDi.koin)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeKtx)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.toolingPreview)

    implementation(libs.jetbrains.kotlinx.collectionsImmutable)
}
