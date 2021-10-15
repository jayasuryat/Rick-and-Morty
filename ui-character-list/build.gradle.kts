plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.apollographql.apollo").version("2.5.9")
}

android {

    compileSdk = 31

    defaultConfig {
        minSdk = 22
        targetSdk = 31

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    buildFeatures {
        viewBinding = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    testImplementation(Dependency.Test.junit)
    androidTestImplementation(Dependency.Test.androidJunit)
    androidTestImplementation(Dependency.Test.espresso)

    implementation(Dependency.appCompat)
    implementation(Dependency.material)
    implementation(Dependency.legacySupport)
    implementation(Dependency.navigationFragment)
    implementation(Dependency.recyclerView)
    implementation(Dependency.glide)

    implementation(Dependency.kotlinxSerialization)

    implementation(Dependency.hilt)
    kapt(Dependency.hiltCompiler)

    implementation(Dependency.roomRuntime)
    implementation(Dependency.roomKtx)
    kapt(Dependency.roomCompiler)

    implementation(Dependency.eventBus)

    implementation(Dependency.apolloRuntime)
    implementation(Dependency.apolloCoroutines)

    api(project(Dependency.Module.baseUi))
    api(project(Dependency.Module.baseData))
}