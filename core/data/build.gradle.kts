plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.googleDaggerHiltAndroid)
    alias(libs.plugins.googleDevtoolsKsp)
    alias(libs.plugins.androidLibrariesMapsplatformSecrets)
}

android {
    namespace = "com.example.data"

    buildFeatures {
        buildConfig = true
    }
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

    // Google services
    implementation(libs.credentials)
    implementation(libs.credentials.play.services.auth)
    implementation(libs.googleid)

    testImplementation(libs.junit)
}