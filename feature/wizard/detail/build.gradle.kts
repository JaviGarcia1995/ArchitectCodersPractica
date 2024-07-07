plugins {
    id("architectcoders.android.feature")
}

android {
    namespace = "com.example.architectcoders.detail"
}

dependencies {
    implementation(project(":domain:wizard"))
}