// Top-level build file where you can add configuration options common to all sub-projects/modules.
apply {
    from("dependencyGraph.gradle")
}
buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }

    dependencies {
        classpath(Dep.androidGradlePlugin)
        classpath(Dep.googleService)
        classpath(Dep.Kotlin.gradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}