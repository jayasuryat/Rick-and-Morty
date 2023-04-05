plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.apollographql.apollo").version(Dependency.apolloVersion)
}

android {

    compileSdk = BuildConfig.compileSdk

    defaultConfig {
        minSdk = BuildConfig.minSdk
        targetSdk = BuildConfig.targetSdk

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
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependency.Compose.composeCompilerVersion
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

    // Arch
    implementation(Dependency.roomRuntime)
    implementation(Dependency.roomKtx)
    kapt(Dependency.roomCompiler)

    // Hilt
    implementation(Dependency.hilt)
    kapt(Dependency.hiltCompiler)

    // Apollo
    implementation(Dependency.apolloRuntime)
    implementation(Dependency.apolloCoroutines)

    implementation(project(Dependency.Module.baseUi))
    implementation(project(Dependency.Module.baseData))
    implementation(project(Dependency.Module.event))
    implementation(project(Dependency.Module.sharedComposable))
    implementation(project(Dependency.Module.themePreview))

    implementation(Dependency.Compose.material)
    implementation(Dependency.Compose.liveData)
    debugImplementation(Dependency.Compose.tooling)
}