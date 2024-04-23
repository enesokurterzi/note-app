plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.googleDaggerHiltAndroid)
    alias(libs.plugins.googleDevtoolsKsp)
}

android {
    namespace = "com.example.domain"
}

dependencies {
    implementation(project(":core:data"))

    api(libs.firebase.auth)

    //Dagger - Hilt
    implementation (libs.hilt)
    ksp (libs.hilt.compiler)

    testImplementation(libs.junit)
}