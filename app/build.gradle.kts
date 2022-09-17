plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {

    compileSdk = BuildConfig.compileSdk

    defaultConfig {
        applicationId = "com.jayasuryat.rickandmorty"
        minSdk = BuildConfig.minSdk
        targetSdk = BuildConfig.targetSdk
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
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependency.Compose.composeCompilerVersion
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.jayasuryat.rickandmorty"
}

dependencies {

    // Test
    testImplementation(Dependency.Test.junit)
    androidTestImplementation(Dependency.Test.androidJunit)
    androidTestImplementation(Dependency.Test.espresso)
    androidTestImplementation(Dependency.Compose.Test.junit)

    // UI
    implementation(Dependency.appCompat)
    implementation(Dependency.material)

    // Hilt
    implementation(Dependency.hilt)
    kapt(Dependency.hiltCompiler)

    // Features
    implementation(project(Dependency.Module.baseData))

    implementation(project(Dependency.Module.baseUi))
    implementation(project(Dependency.Module.event))

    implementation(project(Dependency.Module.home))
    implementation(project(Dependency.Module.characterList))
    implementation(project(Dependency.Module.characterDetails))
    implementation(project(Dependency.Module.episodeList))
    implementation(project(Dependency.Module.episodeDetails))
    implementation(project(Dependency.Module.locationList))
    implementation(project(Dependency.Module.locationDetils))
    implementation(project(Dependency.Module.theme))

    implementation(Dependency.Compose.activity)
    implementation(Dependency.Compose.material)
    implementation(Dependency.Compose.animation)
    debugImplementation(Dependency.Compose.tooling)

    implementation(Dependency.Compose.navigation)
    implementation(Dependency.Compose.hiltNavigation)
}
