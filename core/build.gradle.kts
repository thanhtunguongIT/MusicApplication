plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlin")
    id("kotlin-kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {

    implementation(project(mapOf("path" to ":service")))

    implementation(Dependencies.Core.kotlinStdJdk8)
    implementation(Dependencies.Core.kotlinXCoroutinesAndroid)

    implementation(Dependencies.Networking.RETROFIT)
    implementation(Dependencies.Networking.RETROFIT_GSON)

    implementation(Dependencies.DI.DAGGER)
    kapt(Dependencies.DI.DAGGER_COMPILER)
    implementation(Dependencies.DI.DAGGER_ANDROID)
    kapt(Dependencies.DI.DAGGER_ANDROID_PROCESSOR)
}