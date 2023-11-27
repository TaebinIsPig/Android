plugins {
    id("school-app")
}

android {
    namespace = "com.school.app"

    defaultConfig {
        applicationId = "com.school.app"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
    }
}
dependencies {
    projects.feature.dependencyProject.allprojects.forEachIndexed { index, project ->
        if (index != 0) {
            implementation(project)
        }
    }
}