plugins {
    id("architectcoders.android.feature")
    id("architectcoders.hilt.library.compose")
}

android {
    namespace = "com.example.architectcoders.detail"
}

dependencies {
    implementation(project(":domain:wizard"))
}