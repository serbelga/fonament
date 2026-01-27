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
        namespace = "dev.sergiobelda.fonament.preferences.di.koin"
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

mavenPublishing {
    coordinates(
        artifactId = "fonament-preferences-di-koin",
    )

    publishToMavenCentral(true)

    signAllPublications()
}

dokka {
    dokkaPublications.html {
        moduleName.set("fonament-preferences-di-koin")
    }
}
