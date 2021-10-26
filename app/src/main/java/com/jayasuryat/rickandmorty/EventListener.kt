package com.jayasuryat.rickandmorty

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.*
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.findNavController
import com.jayasuryat.characterdetails.CharacterDetailsEvent
import com.jayasuryat.characterdetails.OpenCharacterEpisodes
import com.jayasuryat.characterlist.CharacterListEvent
import com.jayasuryat.characterlist.NavigateBack
import com.jayasuryat.characterlist.OpenCharacter
import com.jayasuryat.episodedetails.presentation.EpisodeDetailsEvent
import com.jayasuryat.episodelist.presentation.EpisodeListEvent
import com.jayasuryat.episodelist.presentation.OpenEpisode
import com.jayasuryat.home.HomeScreenEvent
import com.jayasuryat.home.OpenCharacters
import com.jayasuryat.home.OpenEpisodes
import com.jayasuryat.home.OpenLocations
import com.jayasuryat.locationdetails.presentation.LocationDetailsEvent
import com.jayasuryat.locationlist.LocationListEvent
import com.jayasuryat.locationlist.OpenLocation
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class EventListener private constructor(
    private val activity: FragmentActivity,
) : LifecycleEventObserver {

    private val navigationHelper: NavigationHelper by lazy { NavigationHelper() }

    init {
        activity.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {

        when (event) {

            ON_START -> register()
            ON_STOP -> unRegister()

            ON_DESTROY -> activity.lifecycle.removeObserver(this)

            ON_CREATE,
            ON_RESUME,
            ON_PAUSE,
            ON_ANY,
            -> Unit
        }
    }

    private fun register() = EventBus.getDefault().register(this)
    private fun unRegister() = EventBus.getDefault().unregister(this)

    // region : Home Screen
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: HomeScreenEvent) {

        when (event) {

            is OpenCharacters -> {

                navigationHelper.navigate(
                    destinationId = R.id.characterListFragment,
                    extras = event.extras,
                )
            }

            is OpenEpisodes -> {

                navigationHelper.navigate(
                    destinationId = R.id.episodesListFragment,
                    extras = event.extras,
                )
            }

            is OpenLocations -> {

                navigationHelper.navigate(
                    destinationId = R.id.locationListFragment,
                    extras = event.extras,
                )
            }

        }.exhaustive
    }
    // endregion

    // region : Character list flow
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: CharacterListEvent) {

        when (event) {

            is OpenCharacter -> {

                val args = Bundle().apply {
                    putLong("id", event.characterId)
                }

                navigationHelper.navigate(
                    destinationId = R.id.characterDetailsFragment,
                    arguments = args,
                    extras = event.extras
                )
            }

            is NavigateBack -> navigationHelper.popBackStack()

        }.exhaustive
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: CharacterDetailsEvent) {

        when (event) {

            is com.jayasuryat.characterdetails.OpenEpisode -> {

                val args = Bundle().apply {
                    putLong("episodeId", event.episodeId)
                }

                navigationHelper.navigate(
                    destinationId = R.id.episodeDetailsFragment,
                    arguments = args,
                    extras = event.extras
                )
            }

            is OpenCharacterEpisodes -> {

                val args = Bundle().apply {
                    putLong("id", event.characterId)
                }

                navigationHelper.navigate(
                    destinationId = R.id.characterEpisodesFragment,
                    arguments = args,
                )
            }

            com.jayasuryat.characterdetails.NavigateBack -> navigationHelper.popBackStack()

        }.exhaustive
    }
    // endregion

    // region : Episode flow
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: EpisodeListEvent) {

        when (event) {

            is OpenEpisode -> {

                val args = Bundle().apply {
                    putLong("episodeId", event.episodeId)
                }

                navigationHelper.navigate(
                    destinationId = R.id.episodeDetailsFragment,
                    arguments = args,
                    extras = event.extras
                )
            }

            is com.jayasuryat.episodelist.presentation.NavigateBack -> navigationHelper.popBackStack()

        }.exhaustive
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: EpisodeDetailsEvent) {

        when (event) {

            com.jayasuryat.episodedetails.presentation.NavigateBack -> navigationHelper.popBackStack()

            is com.jayasuryat.episodedetails.presentation.OpenCharacter -> {

                val args = Bundle().apply {
                    putLong("id", event.characterId)
                }

                navigationHelper.navigate(
                    destinationId = R.id.characterDetailsFragment,
                    arguments = args,
                    extras = event.extras
                )
            }

        }.exhaustive
    }
    // endregion

    // region : Location flow
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: LocationListEvent) {

        when (event) {

            com.jayasuryat.locationlist.NavigateBack -> navigationHelper.popBackStack()

            is OpenLocation -> {

                val args = Bundle().apply {
                    putLong("locationId", event.locationId)
                }

                navigationHelper.navigate(
                    destinationId = R.id.locationDetailsFragment,
                    arguments = args,
                    extras = event.extras
                )
            }
        }.exhaustive
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: LocationDetailsEvent) {

        when (event) {

            com.jayasuryat.locationdetails.presentation.NavigateBack -> navigationHelper.popBackStack()

            is com.jayasuryat.locationdetails.presentation.OpenCharacter -> {

                val args = Bundle().apply {
                    putLong("id", event.characterId)
                }

                navigationHelper.navigate(
                    destinationId = R.id.characterDetailsFragment,
                    arguments = args,
                    extras = event.extras
                )
            }
        }.exhaustive
    }
    //endregion

    private inner class NavigationHelper {

        private val defaultNavOptions: NavOptions by lazy {
            NavOptions.Builder()
                .setEnterAnim(R.anim.enter_from_right)
                .setExitAnim(R.anim.exit_to_left)
                .setPopEnterAnim(R.anim.enter_from_left)
                .setPopExitAnim(R.anim.exit_to_right)
                .build()
        }

        fun navigate(
            @IdRes destinationId: Int,
            arguments: Bundle? = null,
            extras: Navigator.Extras? = null,
            navOptions: NavOptions = defaultNavOptions,
        ) {

            activity.findNavController(R.id.fcvMainContainer)
                .navigate(destinationId, arguments, /*null*/ navOptions, extras)
        }

        fun popBackStack() {

            activity.findNavController(R.id.fcvMainContainer)
                .popBackStack()
        }
    }

    companion object {

        fun FragmentActivity.startListeningForEventsWith() {
            EventListener(this)
        }
    }
}