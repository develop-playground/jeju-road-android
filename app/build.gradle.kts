plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = Versions.compileSdk
    buildToolsVersion = Versions.buildTools

    defaultConfig {
        applicationId = "github.dev_playground.jeju_road"

        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", "\"http://183.107.10.236:8080/api/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding {
            isEnabled = true
            isEnabledForTests = true
        }
    }

    testOptions {
        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }
}

configurations.all {
    resolutionStrategy {
        exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-debug")
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    implementation(Dep.Kotlin.kotlinStdlib)
    implementation(Dep.AndroidX.coreKtx)
    implementation(Dep.AndroidX.appCompat)
    implementation(Dep.material)

    implementation(Dep.Koin.koinAndroid)
    implementation(platform(Dep.Firebase.bom))
    implementation(Dep.Firebase.analytics)
    implementation(Dep.Test.espressoIdlingResource)

    implementation(Dep.AndroidX.Test.androidXTestCore)
    implementation(Dep.AndroidX.Test.fragmentTest)

    testImplementation(Dep.Test.junit)
    testImplementation(Dep.Test.json)
    testImplementation(Dep.Test.mockitoKotlin)
    testImplementation(Dep.Kotlin.Test.coroutine)

    androidTestImplementation(Dep.Test.junitExt)
    androidTestImplementation(Dep.Test.espresso)
    androidTestImplementation(Dep.Test.espressoContrib)
    androidTestImplementation(Dep.Kotlin.Test.coroutine)
    androidTestImplementation(Dep.Test.archTest)
    androidTestImplementation(Dep.Test.mockitoKotlin)
    androidTestImplementation(Dep.Test.dexMakerInline)

    debugImplementation(Dep.AndroidX.Test.fragmentTest)
}