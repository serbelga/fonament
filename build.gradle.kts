plugins {
    alias(deps.plugins.android.application) apply false
    alias(deps.plugins.android.kotlinMultiplatformLibrary) apply false
    alias(deps.plugins.google.dagger.hilt) apply false
    alias(deps.plugins.google.ksp) apply false
    alias(deps.plugins.jetbrains.compose) apply false
    alias(deps.plugins.jetbrains.dokka)
    alias(deps.plugins.jetbrains.kotlin.composeCompiler) apply false
    alias(deps.plugins.jetbrains.kotlin.multiplatform) apply false
    alias(deps.plugins.jetbrains.kotlin.serialization) apply false
    alias(deps.plugins.sergiobelda.convention.spotless) apply false
}

dependencies {
    dokka(projects.fonamentPreferences.preferences)
    dokka(projects.fonamentPreferences.preferencesDi.koin)
    dokka(projects.fonamentPresentation.presentation)
    dokka(projects.fonamentPresentation.presentationDi.koin)
}

apply(from = "./gradle/scripts/git/git-hooks.gradle.kts")
