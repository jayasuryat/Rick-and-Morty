package com.jayasuryat.home

import android.graphics.Point
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.jayasuryat.base.CircleRevealHelper
import com.jayasuryat.base.arch.BaseAbsFragment
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.base.shrinkOnClickWithPoint
import com.jayasuryat.home.databinding.FragmentHomeBinding
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.atomic.AtomicBoolean


class HomeFragment : BaseAbsFragment<HomeViewModel,
        FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val hasLanded: AtomicBoolean = AtomicBoolean(false)

    override val viewModel: HomeViewModel by viewModels()

    override fun setupViews(): FragmentHomeBinding.() -> Unit = {

        binding.clRoot.post(::handleReveal)

        mcvCharacters.shrinkOnClickWithPoint(::navigateToCharacters)
        mcvEpisodes.shrinkOnClick { postEvent(OpenEpisodes) }
        mcvLocations.shrinkOnClick { postEvent(OpenLocations) }
    }

    // region : Reveal animation setup
    private fun handleReveal() {
        if (hasLanded.compareAndSet(false, true)) revealRootInitial()
        else revealRoot()
    }

    private fun revealRootInitial() {

        val animView: View = binding.clRoot

        CircleRevealHelper.Builder(animView)
            .setStartPoint(x = (animView.width / 2).toDouble(), y = 0.0)
            .setEndPoint(x = (animView.width / 2).toDouble(), y = animView.height.toDouble())
            .build()
            .animation
            .start()
    }

    private fun revealRoot() {

        val animView: View = binding.clRoot

        CircleRevealHelper.Builder(animView)
            .setStartPoint(x = 66.0, y = 100.0)
            .setFarthestPointFromStartAsEnd()
            .build()
            .animation
            .start()
    }
    // endregion

    private fun navigateToCharacters(point: Point) {
        val extras = FragmentNavigatorExtras(binding.tvCharacters to "secondTransitionName")
        OpenCharacters(extras = extras, clickPoint = point).let(::postEvent)
    }

    private fun postEvent(event: HomeScreenEvent) = EventBus.getDefault().post(event)
}