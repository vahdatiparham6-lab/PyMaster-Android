plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.chaquo.python")
}

android {
    namespace = "com.parham.pymaster"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.parham.pymaster"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "0.1.0"

        python {
            version = "3.13"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.activity:activity-ktx:1.11.0")
}