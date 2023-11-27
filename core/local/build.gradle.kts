plugins {
    id("school-core")
}

android {
    namespace = "com.school.core.local"
}

dependencies {
    implementation(projects.core.data)
    implementation(libs.androidx.preference)
    implementation(libs.room)
    ksp(libs.room.compiler)
}