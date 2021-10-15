plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
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

    implementation(Dependency.coreKtx)
    implementation(Dependency.appCompat)
    implementation(Dependency.material)
    implementation(Dependency.legacySupport)

    testImplementation(Dependency.Test.junit)
    androidTestImplementation(Dependency.Test.androidJunit)
    androidTestImplementation(Dependency.Test.espresso)

    implementation(Dependency.navigationFragment)
    implementation(Dependency.navigationUi)

    implementation(Dependency.coroutinesAndroid)

    implementation(Dependency.hilt)
    kapt(Dependency.hiltCompiler)

    implementation(Dependency.eventBus)

    implementation(Dependency.glide)

    api(project(Dependency.Module.baseUi))
}