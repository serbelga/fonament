plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.google.dagger.hilt) apply false
    alias(libs.plugins.google.ksp) apply false
    alias(libs.plugins.jetbrains.composeMultiplatform) apply false
    alias(libs.plugins.jetbrains.dokka) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.composeCompiler) apply false
    alias(libs.plugins.jetbrains.kotlin.multiplatform) apply false
    alias(libs.plugins.jetbrains.kotlin.serialization) apply false
    alias(libs.plugins.sergiobelda.convention.spotless) apply false
}
