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
import com.jayasuryat.characterlist.CharacterListEvent
import com.jayasuryat.characterlist.NavigateBack
import com.jayasuryat.characterlist.OpenCharacter
import com.jayasuryat.home.HomeScreenEvent
import com.jayasuryat.home.OpenCharacters
import com.jayasuryat.home.OpenEpisodes
import com.jayasuryat.home.OpenLocations
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class EventListener private constructor(
    private val activity: FragmentActivity
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
            ON_ANY -> Unit
        }
    }

    private fun register() = EventBus.getDefault().register(this)
    private fun unRegister() = EventBus.getDefault().unregister(this)

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: HomeScreenEvent) {

        when (event) {

            is OpenCharacters -> {

                val args = Bundle().apply {
                    putInt("x", event.clickPoint.x)
                    putInt("y", event.clickPoint.y)
                }

                navigationHelper.navigate(
                    destinationId = R.id.characterListFragment,
                    arguments = args,
                    extras = event.extras,
                )
            }

            OpenEpisodes -> TODO()

            OpenLocations -> TODO()
        }
    }

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
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: CharacterDetailsEvent) {

        when (event) {

            com.jayasuryat.characterdetails.NavigateBack -> navigationHelper.popBackStack()
        }
    }

    private inner class NavigationHelper {

        private val defaultNavOptions: NavOptions by lazy {
            NavOptions.Builder()
                .setEnterAnim(R.anim.enter_from_right)
                .setExitAnim(R.anim.exit_to_left)
                .setPopEnterAnim(R.anim.enter_from_left)
                .setPopExitAnim(R.anim.exit_to_right).build()
        }

        fun navigate(
            @IdRes destinationId: Int,
            arguments: Bundle? = null,
            extras: Navigator.Extras? = null,
            navOptions: NavOptions = defaultNavOptions
        ) {

            activity.findNavController(R.id.fcvMainContainer)
                .navigate(destinationId, arguments, null /*navOptions*/, extras)
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