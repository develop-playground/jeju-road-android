plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk
    }
}

dependencies {
    implementation(Dep.Test.junit)
    implementation(Dep.Kotlin.Test.coroutine)
    implementation(Dep.AndroidX.Lifecycle.liveData)
}