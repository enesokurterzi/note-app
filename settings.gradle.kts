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
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS") // to create "projects" from  RootProjectAccessor for better access to modules.
rootProject.name = "Note_App"
include(":app")
include(":core")
include(":core:data")
include(":core:model")
include(":core:domain")
include(":feature")
include(":feature:notes")
include(":feature:add_edit_note")
include(":feature:login")
