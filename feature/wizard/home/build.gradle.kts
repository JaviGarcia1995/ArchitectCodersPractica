plugins {
    id("architectcoders.android.feature")
    id("architectcoders.di.library.compose")
}

android {
    namespace = "com.example.home"
}

dependencies {
    implementation(project(":domain:wizard"))
}