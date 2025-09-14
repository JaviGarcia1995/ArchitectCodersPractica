plugins {
    id("architectcoders.android.library")
    id("architectcoders.android.room")
    id("architectcoders.jvm.retrofit")
    id("architectcoders.hilt.library")
}
android {
    namespace = "com.example.architectcoders.core"
}

dependencies {
    implementation(project(":framework:wizard"))
}