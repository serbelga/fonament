plugins {
    alias(deps.plugins.android.application)
    alias(deps.plugins.google.dagger.hilt)
    alias(deps.plugins.google.ksp)
    alias(deps.plugins.jetbrains.kotlin.android)
    alias(deps.plugins.jetbrains.kotlin.composeCompiler)
    alias(deps.plugins.jetbrains.kotlin.serialization)
    alias(deps.plugins.sergiobelda.convention.spotless)
}

android {
    namespace = "dev.sergiobelda.fonament.demos.hilt"
    compileSdk = deps.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        applicationId = "dev.sergiobelda.fonament.demos.hilt"
        minSdk = deps.versions.androidMinSdk.get().toInt()
        targetSdk = deps.versions.androidTargetSdk.get().toInt()
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
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.fonamentPresentation)
    implementation(projects.fonamentPresentation.samples)

    implementation(deps.androidx.activityCompose)
    implementation(deps.androidx.core.ktx)
    implementation(deps.androidx.lifecycle.runtimeKtx)
    implementation(deps.androidx.lifecycle.viewmodelCompose)
    implementation(deps.androidx.navigation.compose)

    implementation(platform(deps.androidx.compose.bom))
    implementation(deps.androidx.compose.material3)
    implementation(deps.androidx.compose.ui)
    implementation(deps.androidx.compose.uiGraphics)
    implementation(deps.androidx.compose.uiToolingPreview)

    implementation(deps.androidx.hilt.lifecycle.viewmodelCompose)

    implementation(deps.jetbrains.kotlinx.collections.immutable)

    implementation(deps.google.dagger.hilt)
    ksp(deps.google.dagger.hiltCompiler)

    // Workaround for https://github.com/google/dagger/issues/5059
    // TODO: Remove this line
    ksp("org.jetbrains.kotlin:kotlin-metadata-jvm:2.3.0")
}
