plugins {
    id("architectcoders.android.feature")
}

android {
    namespace = "com.example.home"
}

dependencies {
    implementation(project(":domain:wizard"))
}