import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("school-core")
}

android {
    namespace = "com.school.core.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(libs.paging)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.okhttp)
}