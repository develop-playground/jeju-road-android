plugins {
    id 'com.android.library'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-parcelize'
    id 'kotlin-kapt'
}

android {
//    compileSdkVersion 31
//
//    defaultConfig {
//        minSdkVersion 21
//        targetSdkVersion 31
//
//        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles "consumer-rules.pro"
//    }
//
//    buildTypes {
//        release {
//            minifyEnabled false
//            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
//        }
//    }
//    compileOptions {
//        sourceCompatibility JavaVersion.VERSION_1_8
//        targetCompatibility JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        jvmTarget = '1.8'
//    }
    buildFeatures {
        dataBinding = true
    }
//    testOptions {
//        unitTests.returnDefaultValues = true
//    }
}

dependencies {
    implementation project(":domain")

    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.4.1'
    implementation 'com.google.android.material:material:1.5.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.3'
    implementation "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
    implementation "androidx.work:work-runtime-ktx:2.7.1"

    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

    implementation "io.insert-koin:koin-android:$koin_version"
    implementation jetpackDependencies.values()
    implementation coroutineDependencies.values()
    implementation utilDependencies.values()
    implementation androidxDependencies.values()
    kapt kaptDependencies.values()

    testImplementation testDependencies.values()
}