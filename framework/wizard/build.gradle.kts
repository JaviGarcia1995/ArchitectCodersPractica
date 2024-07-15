plugins {
    id("architectcoders.android.library")
    id("architectcoders.android.room")
    id("architectcoders.jvm.retrofit")
    id("architectcoders.di.library")
}

android {
    namespace = "com.example.architectcoders.wizard"
}

dependencies {
    implementation(project(":domain:wizard"))
}