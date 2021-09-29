package com.jayasuryat.home

import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.LinearInterpolator
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.jayasuryat.base.arch.BaseAbsFragment
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.home.UiUtils.setOnClickListenerWithPoint
import com.jayasuryat.home.databinding.FragmentHomeBinding
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.absoluteValue
import kotlin.math.hypot


class HomeFragment : BaseAbsFragment<HomeViewModel,
        FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val hasLanded: AtomicBoolean = AtomicBoolean(false)

    override val viewModel: HomeViewModel by viewModels()

    override fun setupViews(): FragmentHomeBinding.() -> Unit = {

        binding.clRoot.post(::handleReveal)
        /*mcvCharacters.shrinkOnClick {
            postEvent(
                OpenCharacters(
                    extras = FragmentNavigatorExtras(binding.tvCharacters to "secondTransitionName"),
                    point =
                )
            )
        }*/

        mcvCharacters.setOnClickListenerWithPoint { point ->
            postEvent(
                OpenCharacters(
                    extras = FragmentNavigatorExtras(binding.tvCharacters to "secondTransitionName"),
                    point = point
                )
            )
        }
        mcvEpisodes.shrinkOnClick { postEvent(OpenEpisodes) }
        mcvLocations.shrinkOnClick { postEvent(OpenLocations) }
    }

    private fun handleReveal() {

        if (hasLanded.compareAndSet(false, true)) revealRootInitial()
        else revealRoot()
    }

    private fun revealRootInitial() {

        val animView: View = binding.clRoot

        val startX = animView.width / 2
        val startY = animView.height

        val endX = 0
        val endY = 0

        val length = (endX - startX).absoluteValue.toDouble()
        val width = (endY - startY).absoluteValue.toDouble()

        val finalRadius = hypot(length, width).toFloat()

        val anim = ViewAnimationUtils
            .createCircularReveal(animView, startX, startY, 0f, finalRadius)

        anim.duration = 300
        anim.interpolator = LinearInterpolator()

        anim.start()
    }

    private fun revealRoot() {

        val animView: View = binding.clRoot

        val startX = 64
        val startY = 100

        val endX = animView.width
        val endY = animView.height

        val length = (endX - startX).absoluteValue.toDouble()
        val width = (endY - startY).absoluteValue.toDouble()

        val finalRadius = hypot(length, width).toFloat()

        val anim = ViewAnimationUtils
            .createCircularReveal(animView, startX, startY, 0f, finalRadius)

        anim.duration = 300
        anim.interpolator = LinearInterpolator()

        anim.start()
    }

    private fun postEvent(event: HomeScreenEvent) = EventBus.getDefault().post(event)
}