// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.googleDaggerHiltAndroid) apply false
    alias(libs.plugins.googleDevtoolsKsp) apply false
    alias(libs.plugins.googleServices) apply false
    alias(libs.plugins.jetbrainsKotlinJvm) apply false
    alias(libs.plugins.androidLibrary) apply false
}

private typealias AndroidExtension = com.android.build.api.dsl.CommonExtension<*, *, *, *, *, *>

private val Project.androidExtension: AndroidExtension
    get() = extensions.getByType(com.android.build.api.dsl.CommonExtension::class.java)

fun Project.android(block: AndroidExtension.() -> Unit) {
    plugins.withType<com.android.build.gradle.BasePlugin>().configureEach {
        androidExtension.block()
    }
}

private val targetSdkVersion = libs.versions.targetSdk.get().toInt()
private val minSdkVersion = libs.versions.minSdk.get().toInt()
private val bytecodeVersion = JavaVersion.toVersion(libs.versions.jvmBytecode.get())

subprojects {

    // Common Android configurations
    android {
        compileSdk = targetSdkVersion
        defaultConfig {
            vectorDrawables.useSupportLibrary = true

            minSdk = minSdkVersion
        }

        compileOptions {
            sourceCompatibility = bytecodeVersion
            targetCompatibility = bytecodeVersion
        }
    }

    // Configurations for `com.android.application` plugin
    plugins.withType<com.android.build.gradle.AppPlugin>().configureEach {
        extensions.configure<com.android.build.api.dsl.ApplicationExtension> {
            defaultConfig {
                targetSdk = targetSdkVersion
            }
        }
    }

    // Configurations for `com.android.test` plugin
    plugins.withType<com.android.build.gradle.TestPlugin>().configureEach {
        extensions.configure<com.android.build.api.dsl.TestExtension> {
            defaultConfig {
                targetSdk = targetSdkVersion
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = bytecodeVersion.toString()
    }
}