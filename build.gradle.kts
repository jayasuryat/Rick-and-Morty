// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.8.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.45")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
        classpath("com.github.ben-manes:gradle-versions-plugin:0.46.0")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

apply {
    from("buildScripts/versionsPlugin.gradle")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}