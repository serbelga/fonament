pluginManagement {
    includeBuild("gradle/build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "fonament-root"

include(":fonament-di:koin")
include(":fonament-presentation")
include(":fonament-presentation:samples")

include(":demos:basic")
include(":demos:hilt")
include(":demos:koin")
include(":demos:navigation")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
