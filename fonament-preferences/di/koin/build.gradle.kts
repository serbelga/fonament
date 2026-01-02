plugins {
    alias(deps.plugins.android.library)
    alias(deps.plugins.jetbrains.kotlin.multiplatform)
    alias(deps.plugins.vanniktech.mavenpublish)
    alias(deps.plugins.sergiobelda.convention.spotless)
    // alias(deps.plugins.jetbrains.dokka)
}

kotlin {
    androidTarget()
    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            api(projects.fonamentPreferences)

            implementation(project.dependencies.platform(deps.koin.bom))
            implementation(deps.koin.core)
        }
        androidMain.dependencies {
            implementation(deps.koin.android)
        }
    }
}

android {
    namespace = "dev.sergiobelda.fonament.preferences.di.koin"
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

mavenPublishing {
    coordinates(
        artifactId = "fonament-preferences-di-koin",
    )

    publishToMavenCentral(true)

    signAllPublications()
}
