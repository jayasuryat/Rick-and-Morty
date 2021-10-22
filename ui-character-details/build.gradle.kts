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

apollo {
    generateKotlinModels.set(true)
}

dependencies {

    // Test
    testImplementation(Dependency.Test.junit)
    androidTestImplementation(Dependency.Test.androidJunit)
    androidTestImplementation(Dependency.Test.espresso)

    // UI
    implementation(Dependency.appCompat)
    implementation(Dependency.material)

    // Arch components
    implementation(Dependency.navigationFragment)
    // Arch
    implementation(Dependency.roomRuntime)
    implementation(Dependency.roomKtx)
    kapt(Dependency.roomCompiler)

    // Hilt
    implementation(Dependency.hilt)
    kapt(Dependency.hiltCompiler)

    implementation(Dependency.kotlinxSerialization)

    // Others
    implementation(Dependency.glide)
    implementation(Dependency.eventBus)
    // Apollo
    implementation(Dependency.apolloRuntime)
    implementation(Dependency.apolloCoroutines)

    api(project(Dependency.Module.baseUi))
    api(project(Dependency.Module.baseData))
}