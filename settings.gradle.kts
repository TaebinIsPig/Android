enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
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

rootProject.name = "school"
include(":app")
include(":core:domain")
include(":core:data")
include(":core:local")
include(":core:remote")
include(":core:ui")
include(":core:design-system")
include(":feature:intro")
include(":feature:account-management")
include(":feature:signin")
include(":feature:main")
include(":feature:cafeteria")
include(":feature:timetable")
include(":feature:schedule")
include(":feature:profile")
