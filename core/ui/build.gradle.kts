plugins {
    id("school-core")
}

android {
    namespace = "com.school.core.ui"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
}

dependencies {
    implementation(projects.core.designSystem)
    implementation(projects.core.domain)
    implementation(libs.bundles.compose)
    implementation(libs.accompanist.nav)
    implementation(libs.coil)
    debugImplementation(libs.bundles.compose.debug)
}