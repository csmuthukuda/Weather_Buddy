@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("weatherbuddy.android.library")
    id("weatherbuddy.android.hilt")
}

android {
    namespace = "com.csmprojects.core.domain"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:common"))
}