package com.jayasuryat.home

import android.graphics.Point
import android.view.View
import androidx.fragment.app.viewModels
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.jayasuryat.base.anim.AnimHelper
import com.jayasuryat.base.anim.impl.CircleRevealHelper
import com.jayasuryat.base.anim.impl.TranslateAnim
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
        mcvEpisodes.shrinkOnClick(::navigateToEpisodes)
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
            .setStartPoint(x = (animView.width / 2).toDouble(), y = animView.height.toDouble())
            .setEndPoint(x = (animView.width / 2).toDouble(), y = 0.0)
            .setDuration(300)
            .build()
            .animation
            .start()

        AnimHelper.create {
            addAnim {
                TranslateAnim.builder()
                    .fromVerticalDelta(100f)
                    .toCurrentPosition()
                    .build(binding.clContainer)
            }
            addAnim {
                TranslateAnim.builder()
                    .fromVerticalDelta(200f)
                    .toCurrentPosition()
                    .build(binding.clContainer, binding.ivEpisodes, binding.ivLocations)
            }
        }.start()
    }

    private fun revealRoot() {

        val animView: View = binding.clRoot

        CircleRevealHelper.Builder(animView)
            .setStartPoint(x = 66.0, y = 100.0)
            .setFarthestPointFromStartAsEnd()
            .build()
            .animation
        //.start()

        AnimHelper.create {
            addAnim {
                TranslateAnim.builder()
                    .fromVerticalDelta(-100f)
                    .toCurrentPosition()
                    .build(binding.clContainer)
            }
            addAnim {
                TranslateAnim.builder()
                    .fromVerticalDelta(-50f)
                    .toCurrentPosition()
                    .build(binding.clContainer, binding.ivEpisodes, binding.ivLocations)
            }
            overrideInterpolator(LinearOutSlowInInterpolator())
        }.start()
    }
    // endregion

    private fun navigateToCharacters(point: Point) {
        val extras = FragmentNavigatorExtras(binding.tvCharacters to "charListTitle")
        OpenCharacters(extras = extras, clickPoint = point).let(::postEvent)
    }

    private fun navigateToEpisodes() {
        val extras = FragmentNavigatorExtras(binding.tvEpisodes to "episodesListTitle")
        OpenEpisodes(extras = extras).let(::postEvent)
    }

    private fun postEvent(event: HomeScreenEvent) = EventBus.getDefault().post(event)
}