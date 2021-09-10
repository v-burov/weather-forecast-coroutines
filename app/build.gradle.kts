plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("androidx.navigation.safeargs")
}

object Version {
    const val appCompat = "1.3.1"
    const val androidMaterial = "1.4.0"
    const val constraintLayout = "2.1.0"
    const val junit = "4.13.2"
    const val testExtJunit = "1.1.3"
    const val navigation = "2.3.5"
    const val daggerHilt = "2.38.1"
    const val retrofit = "2.9.0"
    const val okkHttp = "4.9.0"
    const val kotlinSerialization = "0.9.1"
    const val kotlinSerializationJson = "1.0.0"
    const val kotlinSerializationConverter = "0.8.0"
    const val swiperefreshlayout = "1.1.0"
    const val liveEvent = "1.2.3"
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "com.example.weather"
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(String::class.java.name, "BASE_URL", "\"https://api.openweathermap.org/\"")
    }

    buildTypes {
        findByName("release")?.apply {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility(1.8)
        targetCompatibility(1.8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

kapt {
    javacOptions {
        // These options are normally set automatically via the Hilt Gradle plugin, but we
        // set them manually to workaround a bug in the Kotlin 1.5.20
        option("-Adagger.fastInit=ENABLED")
        option("-Adagger.hilt.android.internal.disableAndroidSuperclassValidation=true")
    }
}

dependencies {
    implementation("androidx.navigation:navigation-fragment-ktx:${Version.navigation}")
    implementation("androidx.navigation:navigation-ui-ktx:${Version.navigation}")
    implementation("androidx.appcompat:appcompat:${Version.appCompat}")
    implementation("com.google.android.material:material:${Version.androidMaterial}")
    implementation("androidx.constraintlayout:constraintlayout:${Version.constraintLayout}")
    testImplementation("junit:junit:${Version.junit}")
    androidTestImplementation("androidx.test.ext:junit:${Version.testExtJunit}")

    implementation("com.google.dagger:hilt-android:${Version.daggerHilt}")
    kapt("com.google.dagger:hilt-compiler:${Version.daggerHilt}")

    implementation("com.squareup.retrofit2:retrofit:${Version.retrofit}")
    implementation("com.squareup.okhttp3:okhttp:${Version.okkHttp}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Version.okkHttp}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.kotlinSerializationJson}")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Version.kotlinSerializationConverter}")

    implementation("androidx.swiperefreshlayout:swiperefreshlayout:${Version.swiperefreshlayout}")
    implementation("com.github.hadilq:live-event:${Version.liveEvent}")
}
