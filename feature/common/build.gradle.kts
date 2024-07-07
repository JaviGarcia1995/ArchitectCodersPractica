plugins {
    id("architectcoders.android.library.compose")
}

android {
    namespace = "com.example.architectcoders.common"
}

dependencies {
    implementation(project(":domain:wizard"))
    implementation(libs.accompanist)
}