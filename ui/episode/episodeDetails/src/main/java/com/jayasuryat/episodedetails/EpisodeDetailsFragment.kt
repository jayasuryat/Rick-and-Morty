package com.jayasuryat.episodedetails

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.TransitionInflater
import com.jayasuryat.base.arch.BaseAbsFragment
import com.jayasuryat.base.show
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.data.models.domain.Character
import com.jayasuryat.episodedetails.databinding.FragmentEpisodeDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.atomic.AtomicBoolean


@AndroidEntryPoint
class EpisodeDetailsFragment : BaseAbsFragment<EpisodeDetailsViewModel,
        FragmentEpisodeDetailsBinding>(FragmentEpisodeDetailsBinding::inflate) {

    private val hasLanded: AtomicBoolean = AtomicBoolean(false)

    private val characterListAdapter: CharacterListAdapter by lazy {
        CharacterListAdapter(::openCharacter)
    }

    override val viewModel: EpisodeDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (hasLanded.get()) postponeEnterTransition()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun setupViews(): FragmentEpisodeDetailsBinding.() -> Unit = {

        root.post(::animateViews)

        ivBack.shrinkOnClick(::navigateBack)

        rvCharacters.apply {
            layoutManager = GridLayoutManager(requireActivity(), 3)
            adapter = characterListAdapter
        }
    }

    override fun setupObservers(): EpisodeDetailsViewModel.() -> Unit = {

        obsEpisode.observe(viewLifecycleOwner) { episode ->
            if (episode == null) return@observe
            binding.apply {
                tvEpisodeName.text = episode.episodeData.name
                tvAiredOn.text = episode.episodeData.airDate
                cSeason.text = episode.season
                cEpisode.text = episode.episode
            }
        }

        obsCharacter.observe(viewLifecycleOwner) { characters ->
            characterListAdapter.submitList(characters)
            (view?.parent as? ViewGroup)
                ?.doOnPreDraw { startPostponedEnterTransition() }
        }
    }

    private fun animateViews() {

        val animDuration: Long = 300
        val animInterpolator = DecelerateInterpolator()

        fun defaultAnimation() {

            val view = nullableBinding?.cvCharacters ?: return
            val view2 = nullableBinding?.cvInfo ?: return
            val view3 = nullableBinding?.cvName ?: return

            TranslateAnimation(0f, 0f, 200f, 0f)
                .apply {
                    duration = animDuration
                    interpolator = animInterpolator
                }.run {
                    view.startAnimation(this)
                    view2.startAnimation(this)
                    view3.startAnimation(this)
                }

            ObjectAnimator.ofFloat(view, "alpha", 0f, 0.0f, 1f)
                .apply {
                    duration = animDuration
                    interpolator = animInterpolator
                }.start()

            ObjectAnimator.ofFloat(view2, "alpha", 0f, 0.0f, 1f)
                .apply {
                    duration = animDuration
                    interpolator = animInterpolator
                }.start()

            ObjectAnimator.ofFloat(view3, "alpha", 0f, 0.0f, 1f)
                .apply {
                    duration = animDuration
                    interpolator = animInterpolator
                }.start()
        }

        fun backAnimation() {

            val view = nullableBinding?.cvCharacters ?: return
            val view2 = nullableBinding?.cvInfo ?: return
            val view3 = nullableBinding?.cvName ?: return

            TranslateAnimation(0f, 0f, -200f, 0f)
                .apply {
                    duration = animDuration
                    interpolator = animInterpolator
                }.run {
                    view.startAnimation(this)
                    view2.startAnimation(this)
                    view3.startAnimation(this)
                }

            ObjectAnimator.ofFloat(view, "alpha", 0f, 0.0f, 1f)
                .apply {
                    duration = animDuration
                    interpolator = animInterpolator
                }.start()

            ObjectAnimator.ofFloat(view2, "alpha", 0f, 0.0f, 1f)
                .apply {
                    duration = animDuration
                    interpolator = animInterpolator
                }.start()

            ObjectAnimator.ofFloat(view3, "alpha", 0f, 0.0f, 1f)
                .apply {
                    duration = animDuration
                    interpolator = animInterpolator
                }.start()
        }

        if (hasLanded.compareAndSet(false, true)) defaultAnimation()
        else backAnimation()
    }

    private fun openCharacter(character: Character, image: View, name: View, container: View) {

        val extras = FragmentNavigatorExtras(
            image to "characterAvatar",
            name to "characterName",
            container to "characterContainer",
        )

        EventBus.getDefault()
            .post(OpenCharacter(characterId = character.id, extras))
    }

    private fun navigateBack() = EventBus.getDefault().post(NavigateBack)
}