<div align = "center">
  <h1> jeju-road </h1>
</div>

[![Build Status](https://app.bitrise.io/app/08e79fd5bf6fa07f/status.svg?token=WMgAOx82RJyiEZPslYMMqg&branch=master)](https://app.bitrise.io/app/08e79fd5bf6fa07f)
![Android Version](https://img.shields.io/badge/API-23%2B-green?style=flat&logo=Android&logoColor=white")

<p>Jeju Road Application is created for the convenience of travelers on Jeju Island. </br>
Implementation based on MVVM architecture and Clean architecture. Also, Fetch data from the network via repository pattern. 
</p>

<p align="center">
<img src="https://user-images.githubusercontent.com/29699217/169525403-6454f300-212e-4e91-8a3e-cdccf90cf550.png" width="30%"/>
</p>

</br>

## 📊 Android Project Dependency Graph

Creating an Android project dependency graph makes it easier to identify project dependencies. </br>
[It was written with reference to the JakeWharton project file.](https://github.com/JakeWharton/SdkSearch/blob/3351cad9bfacb0a364858e843774147143f58c7a/gradle/projectDependencyGraph.gradle)
  
<p align = "center">
  <img src="project.dot.png"/>
</p>

</br>

## 📚 Stack & Libraries
- IDE : Android Studio
- Minimum SDK level 23
- Language : [Kotlin](https://kotlinlang.org/) based + Transfer build configuration from [Groovy](https://developer.android.com/studio/build/migrate-to-kts) to KTS + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- Jetpack AAC
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) : Observable data holder class.
  - [LifeCycle](https://developer.android.com/topic/libraries/architecture/lifecycle) : Use lifecycle-aware components to perform actions in response to lifecycle events such as activities and fragments(viewmodel, livedata).
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) : Manage data holder classes related to UI and performs asynchronous operations using coroutines for optimal processing.
- Test 
  - [JUnit](https://github.com/junit-team) : Simple framework to write repeatable tests. It is an instance of the xUnit architecture for unit testing frameworks.
  - [Espresso](https://developer.android.com/training/testing/espresso) : Use Espresso to write concise, beautiful, and reliable Android UI tests.
  - [Mockito](https://github.com/mockito/mockito) : Java mocking framework for unit testing.
  - [Roboletric](https://github.com/robolectric/robolectric) : Robolectric is a framework that brings fast and reliable unit tests to Android. Tests run inside the JVM on your workstation in seconds. 
- [Koin](https://github.com/InsertKoinIO/koin) : Dependency injection.
- [Retrofit2](https://github.com/square/retrofit) : REST APIs.
- [OkHttp3](https://github.com/square/okhttp) : Implementing interceptor, logging web server.
- [Glide](https://github.com/bumptech/glide) : Image loader.
- [Gson](https://github.com/google/gson) : Java library that can be used to convert Java Objects into their JSON representation.
- [SwipeRefreshLayout](https://developer.android.com/jetpack/androidx/releases/swiperefreshlayout) : Use to manually refresh the list of restaurants.
- [Shimmer](https://github.com/facebook/shimmer-android) : Use to implement skeleton loading screen

</br>

## 🔭 MAD SCORECARD

<img src="https://user-images.githubusercontent.com/29699217/169535465-710ff6c5-1a82-4a1c-8bdf-8a8df8bc702b.png"/> 
<img src="https://user-images.githubusercontent.com/29699217/169535593-9351c15f-a59d-4885-8018-b33cd664b750.png"/>

</br>

## 🛠 Architecture

Jeju Road is not using the local database yet.

![MVVM](https://user-images.githubusercontent.com/29699217/169539171-b84e7581-ff6e-4a60-b392-c87f3befda8c.png)

![clean](https://user-images.githubusercontent.com/29699217/169539214-f8b0fdb9-08a9-40ab-b577-d619f572c000.png)

</br>

## 📲 API

Refer to the [API document](http://183.107.10.236:8080/docs/index.html) provided by the backend team.

