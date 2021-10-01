package com.jayasuryat.home

import android.graphics.Point
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation
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
            .setStartPoint(x = (animView.width / 2).toDouble(), y = animView.height.toDouble())
            .setEndPoint(x = (animView.width / 2).toDouble(), y = 0.0)
            .setDuration(300)
            .build()
            .animation
            .start()

        TranslateAnimation(0f, 0f, 100f, 0f)
            .apply {
                duration = 300
                interpolator = DecelerateInterpolator()
            }.run { binding.clContainer.startAnimation(this) }

        TranslateAnimation(0f, 0f, 200f, 0f)
            .apply {
                duration = 300
                interpolator = DecelerateInterpolator()
            }.run {
                binding.ivCharacter.startAnimation(this)
                binding.ivEpisodes.startAnimation(this)
                binding.ivLocations.startAnimation(this)
            }
    }

    private fun revealRoot() {

        val animView: View = binding.clRoot

        CircleRevealHelper.Builder(animView)
            .setStartPoint(x = 66.0, y = 100.0)
            .setFarthestPointFromStartAsEnd()
            .build()
            .animation
        //.start()

        TranslateAnimation(0f, 0f, -100f, 0f)
            .apply {
                duration = 300
                interpolator = DecelerateInterpolator()
            }.run { binding.clContainer.startAnimation(this) }

        TranslateAnimation(0f, 0f, -50f, 0f)
            .apply {
                duration = 300
                interpolator = DecelerateInterpolator()
            }.run {
                binding.ivCharacter.startAnimation(this)
                binding.ivEpisodes.startAnimation(this)
                binding.ivLocations.startAnimation(this)
            }
    }
    // endregion

    private fun navigateToCharacters(point: Point) {
        val extras = FragmentNavigatorExtras(binding.tvCharacters to "charListTitle")
        OpenCharacters(extras = extras, clickPoint = point).let(::postEvent)
    }

    private fun postEvent(event: HomeScreenEvent) = EventBus.getDefault().post(event)
}