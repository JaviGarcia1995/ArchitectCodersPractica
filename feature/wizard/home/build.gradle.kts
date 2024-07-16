plugins {
    id("architectcoders.android.feature")
    id("architectcoders.hilt.library.compose")
}

android {
    namespace = "com.example.home"
}

dependencies {
    implementation(project(":domain:wizard"))
}