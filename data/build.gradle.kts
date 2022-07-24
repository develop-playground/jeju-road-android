plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Versions.compileSdk
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":test-module"))
    implementation(Dep.Koin.koinCore)
    implementation(Dep.Koin.koinAndroid)
    implementation(Dep.Square.retrofit)
    implementation(Dep.Square.okhttp)
    implementation(Dep.Square.gsonConverter)
    implementation(Dep.gson)
    implementation(Dep.Test.junitExt)
    implementation(Dep.Test.espresso)
    testImplementation(Dep.Test.junit)
    testImplementation(Dep.Kotlin.Test.coroutine)
    testImplementation(Dep.Test.mockitoKotlin)
    testImplementation(Dep.Test.mockitoInline)
}