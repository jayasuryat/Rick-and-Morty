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

        const val baseData = ":base-data"
        const val home = ":ui-home"
        const val characterList = ":ui-character-list"
        const val characterDetails = ":ui-character-details"
        const val episodeList = ":ui-episode-list"
        const val episodeDetails = ":ui-episode-details"
    }

    const val coreKtx = "androidx.core:core-ktx:1.6.0"

    const val appCompat = "androidx.appcompat:appcompat:1.3.1"
    const val material = "com.google.android.material:material:1.4.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.1"
    const val recyclerView = "androidx.recyclerview:recyclerview:1.2.1"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:2.3.5"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:2.3.5"

    const val hilt = "com.google.dagger:hilt-android:2.38.1"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:2.38.1"

    const val eventBus = "org.greenrobot:eventbus:3.2.0"

    const val recyclerviewAnimators = "jp.wasabeef:recyclerview-animators:4.0.2"

    const val apolloRuntime = "com.apollographql.apollo:apollo-runtime:2.5.9"
    const val apolloCoroutines = "com.apollographql.apollo:apollo-coroutines-support:2.5.9"
    const val kotlinxSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1"

    const val roomRuntime = "androidx.room:room-runtime:2.3.0"
    const val roomKtx = "androidx.room:room-ktx:2.3.0"
    const val roomCompiler = "androidx.room:room-compiler:2.3.0"

    const val ktorAndroid = "io.ktor:ktor-client-android:1.6.3"
    const val ktorCio = "io.ktor:ktor-client-cio:1.6.3"
    const val ktorLogging = "io.ktor:ktor-client-logging:1.6.3"
    const val ktorSerialization = "io.ktor:ktor-client-serialization:1.6.3"
    const val ktorOkhttp = "io.ktor:ktor-client-okhttp:1.6.3"

    const val glide = "com.github.bumptech.glide:glide:4.12.0"
}