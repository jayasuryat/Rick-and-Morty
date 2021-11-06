@file:Suppress("SpellCheckingInspection")

object Dependency {

    object Test {

        const val junit = "junit:junit:4.13.2"
        const val androidJunit = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    }

    object Module {

        const val app = ":app"

        const val baseUi = ":ui-base"
        const val event = ":event"
        const val baseData = ":base-data"
        const val sharedComposable = ":shared-composable"
        const val theme = ":theme"
        const val themePreview = ":theme-preview"

        const val home = ":ui-home"
        const val characterList = ":ui-character-list"
        const val characterDetails = ":ui-character-details"
        const val episodeList = ":ui-episode-list"
        const val episodeDetails = ":ui-episode-details"
        const val locationList = ":ui-location-list"
        const val locationDetils = ":ui-location-details"
    }

    object Compose {

        const val composeVersion = "1.0.1"

        const val activity = "androidx.activity:activity-compose:1.4.0"
        const val material = "androidx.compose.material:material:1.0.4"
        const val animation = "androidx.compose.animation:animation:1.0.4"
        const val tooling = "androidx.compose.ui:ui-tooling:1.0.4"
        const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:1.0.0-alpha03"
        const val paging = "androidx.paging:paging-compose:1.0.0-alpha14"
        const val coil = "io.coil-kt:coil-compose:1.4.0"

        const val liveData = "androidx.compose.runtime:runtime-livedata:1.0.4"
        const val navigation = "androidx.navigation:navigation-compose:2.4.0-beta01"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.0-rc01"

        object Test {

            const val junit = "androidx.compose.ui:ui-test-junit4:1.0.4"
        }
    }

    const val coreKtx = "androidx.core:core-ktx:1.6.0"

    const val appCompat = "androidx.appcompat:appcompat:1.3.1"
    const val material = "com.google.android.material:material:1.4.0"

    const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0"

    const val hilt = "com.google.dagger:hilt-android:2.38.1"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:2.38.1"

    const val apolloVersion = "2.5.10"
    const val apolloRuntime = "com.apollographql.apollo:apollo-runtime:$apolloVersion"
    const val apolloCoroutines = "com.apollographql.apollo:apollo-coroutines-support:$apolloVersion"
    const val kotlinxSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1"

    const val roomRuntime = "androidx.room:room-runtime:2.3.0"
    const val roomKtx = "androidx.room:room-ktx:2.3.0"
    const val roomCompiler = "androidx.room:room-compiler:2.3.0"
    const val roomPaging = "androidx.room:room-paging:2.4.0-beta01"

    const val pagingRuntime = "androidx.paging:paging-runtime:3.1.0-beta01"

    const val coil = "io.coil-kt:coil:1.4.0"
}