package com.jayasuryat.rickandmorty

import android.app.Application
import androidx.viewbinding.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import org.greenrobot.eventbus.EventBus

@HiltAndroidApp
class RickAndMortyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {

        EventBus.builder()
            .throwSubscriberException(BuildConfig.DEBUG)
            .installDefaultEventBus()
    }
}