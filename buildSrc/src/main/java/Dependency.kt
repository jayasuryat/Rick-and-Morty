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

        private const val composeVersion = "1.2.1"
        const val composeCompilerVersion = "1.3.1"

        const val activity = "androidx.activity:activity-compose:1.5.1"
        const val material = "androidx.compose.material:material:$composeVersion"
        const val animation = "androidx.compose.animation:animation:$composeVersion"
        const val tooling = "androidx.compose.ui:ui-tooling:$composeVersion"
        const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:1.0.0"
        const val paging = "androidx.paging:paging-compose:1.0.0-alpha16"
        const val coil = "io.coil-kt:coil-compose:2.2.1"

        const val liveData = "androidx.compose.runtime:runtime-livedata:$composeVersion"
        const val navigation = "androidx.navigation:navigation-compose:2.5.2"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.1"

        object Test {

            const val junit = "androidx.compose.ui:ui-test-junit4:$composeVersion"
        }
    }

    const val coreKtx = "androidx.core:core-ktx:1.9.0"

    const val appCompat = "androidx.appcompat:appcompat:1.5.1"
    const val material = "com.google.android.material:material:1.6.1"

    const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"

    const val hilt = "com.google.dagger:hilt-android:2.43.2"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:2.43.2"

    const val apolloVersion = "2.5.13"
    const val apolloRuntime = "com.apollographql.apollo:apollo-runtime:$apolloVersion"
    const val apolloCoroutines = "com.apollographql.apollo:apollo-coroutines-support:$apolloVersion"
    const val kotlinxSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"

    private const val roomVersion = "2.4.3"
    const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    const val roomPaging = "androidx.room:room-paging:$roomVersion"

    const val pagingRuntime = "androidx.paging:paging-runtime:3.1.1"

    const val coil = "io.coil-kt:coil:2.2.1"
}