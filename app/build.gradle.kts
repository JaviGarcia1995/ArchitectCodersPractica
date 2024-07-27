plugins {
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.ksp)
    id("architectcoders.android.application")
    id("architectcoders.android.application.compose")
    id("architectcoders.hilt.library.compose")
}

android {
    namespace = "com.example.architectcoderspracticauno"

    defaultConfig {
        applicationId = "com.example.architectcoderspracticauno"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.architectcoderspracticauno.dependencies.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":domain:wizard"))
    implementation(project(":framework:core"))
    implementation(project(":framework:wizard"))
    implementation(project(":feature:wizard:home"))
    implementation(project(":feature:wizard:detail"))
    implementation(libs.androidx.activity.compose)
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.compiler)
    androidTestImplementation(libs.androidx.room.ktx)
    kspAndroidTest(libs.androidx.room.compiler)
    androidTestImplementation(libs.okhttp.mockwebserver)
}