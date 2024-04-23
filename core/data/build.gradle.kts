plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.googleDaggerHiltAndroid)
    alias(libs.plugins.googleDevtoolsKsp)
}

android {
    namespace = "com.example.data"
}

dependencies {
    api(project(":core:model"))

    implementation(libs.jetbrains.kotlinx.coroutines.core)

    // Firebase
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.auth)

    //Dagger - Hilt
    implementation (libs.hilt)
    ksp (libs.hilt.compiler)

    testImplementation(libs.junit)
}