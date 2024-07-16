plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication"){
            id = "architectcoders.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose"){
            id = "architectcoders.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary"){
            id = "architectcoders.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose"){
            id = "architectcoders.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeature"){
            id = "architectcoders.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("jvmLibrary"){
            id = "architectcoders.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("jvmRetrofit"){
            id = "architectcoders.jvm.retrofit"
            implementationClass = "JvmRetrofitConventionPlugin"
        }
        register("androidRoom"){
            id = "architectcoders.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("hiltLibrary"){
            id = "architectcoders.hilt.library"
            implementationClass = "HiltLibraryConventionPlugin"
        }
        register("hiltLibraryCompose"){
            id = "architectcoders.hilt.library.compose"
            implementationClass = "HiltLibraryComposeConventionPlugin"
        }
    }
}