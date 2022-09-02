plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Versions.compileSdk
}

dependencies {
    implementation(project(":domain"))
    implementation(Dep.Koin.koinCore)
    implementation(Dep.Koin.koinAndroid)
    implementation(Dep.Square.retrofit)
    implementation(Dep.Square.okhttp)
    implementation(Dep.Square.gsonConverter)
    implementation(Dep.gson)
    implementation(Dep.Test.junitExt)
    implementation(Dep.Test.espresso)
    implementation(Dep.Logger.logger)
}