pluginManagement {
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
    versionCatalogs {
        create("deps") {
            from("dev.sergiobelda.projectconfig.catalog:deps:2026.02.01")
        }
    }
}

rootProject.name = "fonament-root"

include(":fonament-preferences")
include(":fonament-preferences:di:koin")
include(":fonament-presentation")
include(":fonament-presentation:di:koin")
include(":fonament-presentation:samples")

include(":demos:basic")
include(":demos:hilt")
include(":demos:koin")
include(":demos:navigation")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
