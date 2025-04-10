Anime App
A simple anime app that fetches and displays anime data using the Jikan API. The app showcases a list of top anime titles and provides detailed information for each selected anime. The app is built using MVVM architecture, Retrofit for network calls, Dagger for dependency injection, and Kotlin Flow for managing asynchronous data streams.

Features
Displays a list of top anime with titles, images, and descriptions.

Shows detailed information for each anime, including synopsis, score, and genres.

Uses MVVM architecture for clean separation of concerns.

Dagger is used for dependency injection.

Retrofit and Kotlin Flow for API data handling.

Handles loading, success, and error states in the UI.

Tech Stack
Android: Kotlin

MVVM Architecture: ViewModel, StateFlow

Dependency Injection: Dagger

API Client: Retrofit

State Management: Kotlin Flow

Network State Management: Coroutine-based Async

UI: Material Design components

Setup Instructions
1. Clone the Repository
Clone this repository to your local machine:

2. Open the Project
Open the project in Android Studio.

3. Install Dependencies
In Android Studio, go to File > Sync Project with Gradle Files to install required dependencies.

4. API Configuration
The app uses the Jikan API to fetch anime data. No API key is required to use the public API.

5. Run the App
Run the app on an emulator or physical device.

Troubleshooting: If the App Doesn't Build Successfully
If the app fails to build or compile correctly, it could be due to an issue with the versions of Kotlin or Dagger used in your project. Follow these steps to ensure everything is configured correctly:

1. Check Kotlin Version
Ensure that your Kotlin version is compatible with the project. You can check the version by opening the build.gradle file in the root directory of the project and ensuring that the Kotlin version is correctly specified.

For example:

gradle
buildscript {
    ext {
        kotlin_version = '1.8.0'  // Update to a version compatible with your Android Studio
    }
    ...
}
If needed, update the Kotlin version to match the version supported by your Android Studio version.

2. Check Dagger Version
Ensure that the version of Dagger in your project is compatible with your setup. You can check the version in the build.gradle file (usually in the dependencies block).

For example:

gradle
dependencies {
    implementation 'com.google.dagger:dagger:2.40.5' // Use a version compatible with your setup
    annotationProcessor 'com.google.dagger:dagger-compiler:2.40.5' // Update if necessary
}
If needed, update the Dagger version according to the compatibility with your Android Studio setup.

3. Sync Gradle Files
After making changes to either the Kotlin or Dagger versions, sync the Gradle files again:

Go to File > Sync Project with Gradle Files.

