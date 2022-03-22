plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlin-kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {

    implementation(Dependencies.Core.kotlinXCoroutinesAndroid)

    implementation(Dependencies.Networking.RETROFIT)
    implementation(Dependencies.Networking.RETROFIT_GSON)

    implementation(Dependencies.DI.DAGGER)
    kapt(Dependencies.DI.DAGGER_COMPILER)
    implementation(Dependencies.DI.DAGGER_ANDROID)
    kapt(Dependencies.DI.DAGGER_ANDROID_PROCESSOR)
}