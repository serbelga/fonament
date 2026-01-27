import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(deps.plugins.android.kotlinMultiplatformLibrary)
    alias(deps.plugins.jetbrains.dokka)
    alias(deps.plugins.jetbrains.kotlin.multiplatform)
    alias(deps.plugins.sergiobelda.convention.spotless)
    alias(deps.plugins.vanniktech.mavenpublish)
}

kotlin {
    androidLibrary {
        namespace = "dev.sergiobelda.fonament.preferences"
        compileSdk = deps.versions.android.compileSdk.get().toInt()
        minSdk = deps.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }

        withHostTest {
            isIncludeAndroidResources = true
        }
    }
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
        getByName("androidHostTest") {
            dependencies {
                implementation(deps.junit)
                implementation(deps.mockk.mockk)
            }
        }
    }
}

mavenPublishing {
    publishToMavenCentral(true)

    signAllPublications()
}
