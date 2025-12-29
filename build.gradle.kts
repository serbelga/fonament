plugins {
    alias(deps.plugins.android.application) apply false
    alias(deps.plugins.android.library) apply false
    alias(deps.plugins.google.dagger.hilt) apply false
    alias(deps.plugins.google.ksp) apply false
    alias(deps.plugins.jetbrains.composeMultiplatform) apply false
    alias(deps.plugins.jetbrains.dokka) apply false
    alias(deps.plugins.jetbrains.kotlin.android) apply false
    alias(deps.plugins.jetbrains.kotlin.composeCompiler) apply false
    alias(deps.plugins.jetbrains.kotlin.multiplatform) apply false
    alias(deps.plugins.jetbrains.kotlin.serialization) apply false
    alias(deps.plugins.sergiobelda.convention.spotless) apply false
}
