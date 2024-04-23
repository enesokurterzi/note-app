plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.model"
}

dependencies {
    implementation(libs.androidx.activity.compose)
}