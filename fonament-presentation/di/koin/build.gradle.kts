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
        namespace = "dev.sergiobelda.fonament.presentation.di.koin"
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
            api(projects.fonamentPresentation)

            implementation(project.dependencies.platform(deps.koin.bom))
            implementation(deps.koin.core)
            implementation(deps.koin.composeViewmodel)

            implementation(deps.jetbrains.androidx.lifecycle.viewmodelCompose)
            implementation(deps.jetbrains.compose.ui)
        }
        androidMain.dependencies {
            implementation(deps.androidx.core.ktx)
        }
    }
}

mavenPublishing {
    coordinates(
        artifactId = "fonament-presentation-di-koin",
    )

    publishToMavenCentral(true)

    signAllPublications()
}

dokka {
    dokkaPublications.html {
        moduleName.set("fonament-presentation-di-koin")
    }
}
