plugins {
    alias(deps.plugins.android.library)
    alias(deps.plugins.jetbrains.compose)
    alias(deps.plugins.jetbrains.dokka)
    alias(deps.plugins.jetbrains.kotlin.composeCompiler)
    alias(deps.plugins.jetbrains.kotlin.multiplatform)
    alias(deps.plugins.sergiobelda.convention.spotless)
    alias(deps.plugins.vanniktech.mavenpublish)
}

kotlin {
    androidTarget()
    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

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

android {
    namespace = "dev.sergiobelda.fonament.presentation.di.koin"
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
        artifactId = "fonament-presentation-di-koin",
    )

    publishToMavenCentral(true)

    signAllPublications()
}
