plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {

    compileSdk = 31

    defaultConfig {
        applicationId = "com.jayasuryat.rickandmorty"
        minSdk = 22
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

    kapt {
        correctErrorTypes = true
        javacOptions {
            // Increase the max count of errors from annotation processors.
            // Default is 100.
            option("-Xmaxerrs", 500)
        }
    }
}

dependencies {

    // Test
    testImplementation(Dependency.Test.junit)
    androidTestImplementation(Dependency.Test.androidJunit)
    androidTestImplementation(Dependency.Test.espresso)

    // UI
    implementation(Dependency.appCompat)
    implementation(Dependency.material)
    implementation(Dependency.constraintLayout)

    // Arch components
    implementation(Dependency.navigationFragment)
    implementation(Dependency.navigationUi)

    // Hilt
    implementation(Dependency.hilt)
    kapt(Dependency.hiltCompiler)

    // Others
    implementation(Dependency.eventBus)

    // Features
    implementation(project(Dependency.Module.data))
    implementation(project(Dependency.Module.baseData))

    implementation(project(Dependency.Module.baseUi))

    implementation(project(Dependency.Module.home))
    implementation(project(Dependency.Module.characterList))
    implementation(project(Dependency.Module.characterDetails))
    implementation(project(Dependency.Module.episodeList))
    implementation(project(Dependency.Module.episodeDetails))
}
