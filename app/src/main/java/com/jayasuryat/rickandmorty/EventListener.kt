package com.jayasuryat.rickandmorty

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.*
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.findNavController
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

            is OpenCharacters -> (R.id.characterListFragment)
                .navigate(event.extras, Bundle().apply {
                    putInt("x", event.point.x)
                    putInt("y", event.point.y)
                })

            OpenEpisodes -> TODO()

            OpenLocations -> TODO()
        }
    }

    private fun NavDirections.navigate() =
        activity.findNavController(R.id.fcvMainContainer).navigate(this)

    private fun Int.navigate(extras: Navigator.Extras, args: Bundle? = null) =
        activity.findNavController(R.id.fcvMainContainer)
            .navigate(
                this, args,
                NavOptions.Builder()
                    .setEnterAnim(R.anim.enter_from_right)
                    .setExitAnim(R.anim.exit_to_right)
                    .setPopEnterAnim(R.anim.enter_from_left)
                    .setPopExitAnim(R.anim.exit_to_right).build(),
                extras
            )


    companion object {

        fun FragmentActivity.startListeningForEventsWith() {
            EventListener(this)
        }
    }
}