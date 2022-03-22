object Dependencies {

    object Core {

        const val kotlinCore = "androidx.core:core-ktx:${DependencyVersions.KOTLIN_VERSION}"

        const val kotlinStdJdk8 =
            "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${DependencyVersions.KOTLIN_STD_JDK8}"

        const val kotlinXCoroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${DependencyVersions.KOTLIN_X_COROUTINES_ANDROID_VERSION}"
    }

    object LifeCycle {

        const val VIEW_MODEL =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${DependencyVersions.LIFECYCLE_VERSION}"

        const val LIVE_DATA =
            "androidx.lifecycle:lifecycle-livedata-ktx:${DependencyVersions.LIFECYCLE_VERSION}"

        const val EXTENSION =
            "androidx.lifecycle:lifecycle-extensions:${DependencyVersions.LIFECYCLE_EXTENSION_VERSION}"
    }

    object DI {

        const val DAGGER = "com.google.dagger:dagger:${DependencyVersions.DAGGER_VERSION}"

        const val DAGGER_COMPILER =
            "com.google.dagger:dagger-compiler:${DependencyVersions.DAGGER_VERSION}"

        const val DAGGER_ANDROID =
            "com.google.dagger:dagger-android:${DependencyVersions.DAGGER_VERSION}"

        const val DAGGER_ANDROID_PROCESSOR =
            "com.google.dagger:dagger-android-processor:${DependencyVersions.DAGGER_VERSION}"
    }

    object Networking {

        const val RETROFIT =
            "com.squareup.retrofit2:retrofit:${DependencyVersions.RETROFIT_VERSION}"

        const val RETROFIT_GSON =
            "com.squareup.retrofit2:converter-gson:${DependencyVersions.RETROFIT_VERSION}"
    }

    object Jetpack {

        const val FRAGMENT = "androidx.fragment:fragment-ktx:${DependencyVersions.JETPACK_VERSION}"

        const val RECYCLER_VIEW =
            "androidx.recyclerview:recyclerview:${DependencyVersions.RECYCLER_VIEW_VERSION}"

        const val NAVIGATION =
            "androidx.navigation:navigation-fragment-ktx:${DependencyVersions.NAVIGATION_VERSION}"

        const val NAVIGATION_UI =
            "androidx.navigation:navigation-ui-ktx:${DependencyVersions.NAVIGATION_VERSION}"

        const val PAGING_3 =
            "androidx.paging:paging-runtime-ktx:${DependencyVersions.PAGING_VERSION}"
    }

    object UI {

        const val APP_COMPAT =
            "androidx.appcompat:appcompat:${DependencyVersions.APP_COMPAT_VERSION}"

        const val GOOGLE_MATERIAL_DESIGN =
            "com.google.android.material:material:${DependencyVersions.MATERIAL_VERSION}"

        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${DependencyVersions.CONSTRAINT_LAYOUT_VERSION}"

        const val VIEW_PAGER_2 =
            "androidx.viewpager2:viewpager2:${DependencyVersions.VIEW_PAGER_2_VERSION}"

        const val GLIDE = "com.github.bumptech.glide:glide:${DependencyVersions.GLIDE_VERSION}"

        const val GLIDE_COMPILER =
            "com.github.bumptech.glide:compiler:${DependencyVersions.GLIDE_VERSION}"

        const val SQUIRCLE_VIEW = "app.juky:squircleview:${DependencyVersions.SQUIRCLE_VERSION}"
    }

    object UnitTest {

        const val JUNIT = "junit:junit:${DependencyVersions.JUNIT_VERSION}"

        const val ANDROID_X_JUNIT =
            "androidx.test.ext:junit:${DependencyVersions.ANDROID_X_JUNIT_VERSION}"

        const val ESPRESSO_CORE =
            "androidx.test.espresso:espresso-core:${DependencyVersions.ESPRESSO_CORE_VERSION}"

        const val MOCKITO_CORE = "org.mockito:mockito-core:${DependencyVersions.MOCKITO_CORE}"

        const val MOCKITO_KOTLIN =
            "org.mockito.kotlin:mockito-kotlin:${DependencyVersions.MOCKITO_KOTLIN_VERSION}"

        const val MOCK_WEB_SERVER =
            "com.squareup.okhttp3:mockwebserver:${DependencyVersions.MOCK_WEB_SERVER_VERSION}"

        const val CORE_TESTING =
            "androidx.arch.core:core-testing:${DependencyVersions.CORE_TESTING_VERSION}"

        const val KOTLIN_COROUTINES_TEST =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${DependencyVersions.KOTLIN_COROUTINES_TEST}"
    }
}