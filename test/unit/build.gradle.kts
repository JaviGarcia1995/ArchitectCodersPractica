plugins {
    id("architectcoders.jvm.library")
}

dependencies {
    implementation(project(":domain:wizard"))
    implementation(libs.junit)
    implementation(libs.kotlinx.coroutines.test)
}