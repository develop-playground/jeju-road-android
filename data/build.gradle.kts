plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {}

dependencies {
    implementation(project(":domain"))
    implementation(Dep.Koin.koinCore)
    implementation(Dep.Square.retrofit)
    implementation(Dep.Square.okhttp)
    implementation(Dep.Square.gsonConverter)
    implementation(Dep.Test.junit)
    implementation(Dep.Test.espresso)
}