plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.tung.musicapplication"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        getByName("release") {
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
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(project(Modules.CORE))
    implementation(project(Modules.SERVICE))

    implementation(Dependencies.DI.DAGGER)
    kapt(Dependencies.DI.DAGGER_COMPILER)
    implementation(Dependencies.DI.DAGGER_ANDROID)
    kapt(Dependencies.DI.DAGGER_ANDROID_PROCESSOR)

    implementation(Dependencies.Networking.RETROFIT)
    implementation(Dependencies.Networking.RETROFIT_GSON)

    implementation(Dependencies.Core.kotlinCore)
    implementation(Dependencies.LifeCycle.VIEW_MODEL)
    implementation(Dependencies.LifeCycle.LIVE_DATA)
    implementation(Dependencies.LifeCycle.EXTENSION)

    implementation(Dependencies.Jetpack.FRAGMENT)
    implementation(Dependencies.Jetpack.RECYCLER_VIEW)
    implementation(Dependencies.Jetpack.NAVIGATION)
    implementation(Dependencies.Jetpack.NAVIGATION_UI)
    implementation(Dependencies.Jetpack.PAGING_3)

    implementation(Dependencies.UI.APP_COMPAT)
    implementation(Dependencies.UI.GOOGLE_MATERIAL_DESIGN)
    implementation(Dependencies.UI.CONSTRAINT_LAYOUT)
    implementation(Dependencies.UI.VIEW_PAGER_2)
    implementation(Dependencies.UI.GLIDE)
    kapt(Dependencies.UI.GLIDE_COMPILER)
    implementation(Dependencies.UI.SQUIRCLE_VIEW)

    testImplementation(Dependencies.UnitTest.JUNIT)
    testImplementation(Dependencies.UnitTest.MOCKITO_CORE)
    testImplementation(Dependencies.UnitTest.MOCKITO_KOTLIN)
    testImplementation(Dependencies.UnitTest.MOCK_WEB_SERVER)
    testImplementation(Dependencies.UnitTest.CORE_TESTING)
    testImplementation(Dependencies.UnitTest.KOTLIN_COROUTINES_TEST)
    androidTestImplementation(Dependencies.UnitTest.ANDROID_X_JUNIT)
    androidTestImplementation(Dependencies.UnitTest.ESPRESSO_CORE)
}