plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.apollographql.apollo").version("2.5.9")
    id("kotlin-android")
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
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

apollo {
    generateKotlinModels.set(true)
}

tasks.withType(org.jetbrains.kotlin.gradle.dsl.KotlinCompile::class).all {
    kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
}

dependencies {

    // Test
    testImplementation(Dependency.Test.junit)
    androidTestImplementation(Dependency.Test.androidJunit)
    androidTestImplementation(Dependency.Test.espresso)

    // UI
    implementation(Dependency.appCompat)
    implementation(Dependency.material)
    implementation(Dependency.recyclerView)

    // Arch components
    implementation(Dependency.navigationFragment)
    implementation(Dependency.lifecycleKtx)

    // Room
    implementation(Dependency.roomRuntime)
    implementation(Dependency.roomKtx)
    kapt(Dependency.roomCompiler)
    implementation(Dependency.roomPaging)
    implementation(Dependency.pagingRuntime)

    // Hilt
    implementation(Dependency.hilt)
    kapt(Dependency.hiltCompiler)

    // Others
    implementation(Dependency.eventBus)
    implementation(Dependency.coil)
    implementation(Dependency.recyclerviewAnimators)
    // Apollo
    implementation(Dependency.apolloRuntime)
    implementation(Dependency.apolloCoroutines)

    implementation(project(Dependency.Module.baseUi))
    implementation(project(Dependency.Module.baseData))
    implementation(project(Dependency.Module.event))
    implementation(project(Dependency.Module.sharedComposable))

    implementation(Dependency.Compose.material)
    implementation(Dependency.Compose.paging)
    implementation(Dependency.Compose.hiltNavigation)
    debugImplementation(Dependency.Compose.tooling)
}