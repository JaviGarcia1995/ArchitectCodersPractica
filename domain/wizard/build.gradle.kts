plugins {
    id("architectcoders.jvm.library")
    id("architectcoders.di.library")
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}