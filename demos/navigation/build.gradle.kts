plugins {
    alias(deps.plugins.android.application)
    alias(deps.plugins.jetbrains.kotlin.composeCompiler)
    alias(deps.plugins.jetbrains.kotlin.serialization)
    alias(deps.plugins.sergiobelda.convention.spotless)
}

android {
    namespace = "dev.sergiobelda.fonament.demos.navigation"
    compileSdk = deps.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "dev.sergiobelda.fonament.demos.navigation"
        minSdk = deps.versions.android.minSdk.get().toInt()
        targetSdk = deps.versions.android.targetSdk.get().toInt()
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

    implementation(deps.jetbrains.kotlinx.collections.immutable)
}
