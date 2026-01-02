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
            implementation(deps.androidx.datastore.preferences)
            implementation(deps.touchlab.stately.concurrentCollections)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(deps.jetbrains.kotlinx.coroutines.test)
        }
        androidUnitTest.dependencies {
            implementation(deps.junit)
            implementation(deps.mockk.mockk)
        }
    }
}

android {
    namespace = "dev.sergiobelda.fonament.preferences"
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
    publishToMavenCentral(true)

    signAllPublications()
}
