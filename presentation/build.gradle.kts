plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    compileSdk = Versions.compileSdk
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(Dep.material)

    implementation(Dep.AndroidX.activity)
    implementation(Dep.AndroidX.fragment)
    implementation(Dep.AndroidX.constraintLayout)
    implementation(Dep.AndroidX.swipeRefreshLayout)
    implementation(Dep.AndroidX.recyclerView)
    implementation(Dep.AndroidX.recyclerViewSelection)
    implementation(Dep.AndroidX.coreKtx)
    implementation(Dep.AndroidX.Lifecycle.liveData)
    implementation(Dep.AndroidX.Lifecycle.viewModel)

    implementation(Dep.Koin.koinAndroid)
    implementation(Dep.Kotlin.coroutineAndroid)
    implementation(Dep.photoView)
    implementation(Dep.Glide.glide)
    implementation(Dep.Glide.glideCompiler)

    testImplementation(Dep.Kotlin.Test.coroutine)
    testImplementation(Dep.Koin.Test.koinTest)
    testImplementation(Dep.Test.junit)
    androidTestImplementation(Dep.Test.junitExt)
    androidTestImplementation(Dep.Test.espresso)
}