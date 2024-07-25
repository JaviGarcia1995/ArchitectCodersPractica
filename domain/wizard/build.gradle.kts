plugins {
    id("architectcoders.jvm.library")
    id("architectcoders.hilt.library")
}

dependencies {
    testImplementation(project(":test:unit"))
    testImplementation(libs.junit.junit)
}