object Versions {
    const val compileSdk = 31
    const val buildTools = "30.0.3"

    const val minSdk = 23
    const val targetSdk = 31
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Dep {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.1.2"
    const val googleService = "com.google.gms:google-services:4.3.10"
    const val material = "com.google.android.material:material:1.5.0"

    object Kotlin {
        const val version = "1.5.0"

        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"

        object Test {
            const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    object AndroidX {

        object Lifecycle {
            private const val lifecycleVersion = "2.3.1"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
            const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        }

        const val activity = "androidx.activity:activity-ktx:1.3.1"
        const val fragment = "androidx.fragment:fragment-ktx:1.3.6"
        const val recyclerView = "androidx.recyclerview:recyclerview:1.2.1"
        const val recyclerViewSelection = "androidx.recyclerview:recyclerview-selection:1.1.0"
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val appCompat = "androidx.appcompat:appcompat:1.4.1"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.3"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"

        private const val roomVersion = "2.3.0"

        const val room = "androidx.room:room-runtime:$roomVersion"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"

        object Test {
            const val androidXTestCore = "androidx.test:core:1.3.0"
            const val fragmentTest = "androidx.fragment:fragment-testing:1.4.1"
        }
    }

    object Square {
        private const val retrofitVersion = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val okhttp = "com.squareup.okhttp3:logging-interceptor:4.8.1"
    }

    object Koin {
        private const val version = "3.1.5"

        const val koinAndroid = "io.insert-koin:koin-android:$version"
        const val koinCore = "io.insert-koin:koin-core:$version"

        object Test {
            const val koinTest = "io.insert-koin:koin-test:$version"
            const val koinTestJunit4 = "io.insert-koin:koin-test-junit4:$version"
        }
    }

    object Test {
        private const val version = "3.4.0"

        const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:4.0.0"
        const val mockitoCore = "org.mockito:mockito-core:4.6.1"
        const val mockitoAndroid = "org.mockito:mockito-android:2.25.0"
        const val mockitoInline = "org.mockito:mockito-inline:2.13.0"
        const val json = "org.json:json:20210307"
        const val junit = "junit:junit:4.13.2"
        const val junitExt = "androidx.test.ext:junit:1.1.3"
        const val archTest = "androidx.arch.core:core-testing:2.1.0"
        const val dexMakerInline = "com.linkedin.dexmaker:dexmaker-mockito-inline-extended:2.28.1"
        const val espresso = "androidx.test.espresso:espresso-core:$version"
        const val espressoContrib = "androidx.test.espresso:espresso-contrib:$version"
        const val espressoIdlingResource = "androidx.test.espresso:espresso-idling-resource:$version"
        const val testRunner = "androidx.test:runner:1.1.0"
        const val testRules = "androidx.test:rules:1.1.0"
    }

    object Glide {
        private const val version = "4.11.0"
        const val glide = "com.github.bumptech.glide:glide:$version"
        const val glideCompiler = "com.github.bumptech.glide:compiler:$version"
    }

    object Firebase {
        const val bom = "com.google.firebase:firebase-bom:29.0.3"
        const val analytics = "com.google.firebase:firebase-analytics-ktx"
    }

    object Logger {
        private const val version = "2.2.0"
        const val logger = "com.orhanobut:logger:$version"
    }

    const val lottie = "com.airbnb.android:lottie:5.0.3"
    const val gson = "com.google.code.gson:gson:2.8.7"
    const val photoView = "com.github.chrisbanes:PhotoView:2.3.0"
    const val shimmer =  "com.facebook.shimmer:shimmer:0.5.0"

}
