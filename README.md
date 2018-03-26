# ArcTouch Challenge

## Table of contents
- [Build Instructions](#build-instructions)
- [Libraries](#libraries)


## Build Instructions
This project was built using Kotlin, so be sure to have the latest Kotlin plugin version installed.

This project targets the sdk version 27, so be sure to have that updated as well and the repesctive support library

## Libraries

- Retrofit2: Used to make all the API calls
    - Retrofit2 Gson Converter: To allow retrofit to parse not only primitive types
    - Retrofit2 RxJava Adapter: So Retrofit can return Observables and other RxJava types

- RxJava 2: To allow reactive application flows and to make switching thread easier
    - RxAndroid: For the Android Schedulers when switching threads

- Picasso: For async image loading and easy caching

- Android Lifecycle Extensions (ViewModel and LiveData): To make it easier to update the UI and allow configuration changes (like rotation)

- OkHttp3: Used as a client for Picasso and Retrofit2
    - OkHttp3 Logging Interceptor: To log request in debug mode
    - OkHttp3 Downloader: To allow Picasso to cache images
