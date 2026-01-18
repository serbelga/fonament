import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(deps.plugins.android.kotlinMultiplatformLibrary)
    alias(deps.plugins.jetbrains.compose)
    alias(deps.plugins.jetbrains.dokka)
    alias(deps.plugins.jetbrains.kotlin.composeCompiler)
    alias(deps.plugins.jetbrains.kotlin.multiplatform)
    alias(deps.plugins.sergiobelda.convention.spotless)
    alias(deps.plugins.vanniktech.mavenpublish)
}

kotlin {
    androidLibrary {
        namespace = "dev.sergiobelda.fonament"
        compileSdk = deps.versions.android.compileSdk.get().toInt()
        minSdk = deps.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(deps.jetbrains.compose.foundation)
            implementation(deps.jetbrains.compose.ui)
            implementation(deps.androidx.lifecycle.viewmodel)
        }
        androidMain.dependencies {
            implementation(deps.androidx.core.ktx)
        }
    }
}

tasks.withType<Jar> {
    from(file("$rootDir/${projects.fonamentPresentation.name}/samples/src/commonMain/kotlin"))
}

mavenPublishing {
    publishToMavenCentral(true)

    signAllPublications()
}
