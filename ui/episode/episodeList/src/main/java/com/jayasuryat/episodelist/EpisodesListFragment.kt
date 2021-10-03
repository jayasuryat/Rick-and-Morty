package com.jayasuryat.episodelist

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.jayasuryat.base.arch.BaseAbsFragment
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.episodelist.databinding.FragmentEpisodesListBinding
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.atomic.AtomicBoolean


@AndroidEntryPoint
class EpisodesListFragment : BaseAbsFragment<EpisodesListViewModel,
        FragmentEpisodesListBinding>(FragmentEpisodesListBinding::inflate) {

    private val hasLanded: AtomicBoolean = AtomicBoolean(false)

    private val episodesListAdapter: EpisodesListAdapter by lazy {
        EpisodesListAdapter(::onSeasonClicked, ::onEpisodeClicked)
    }

    override val viewModel: EpisodesListViewModel by viewModels()

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

    override fun setupViews(): FragmentEpisodesListBinding.() -> Unit = {

        root.post(::handleAnimation)

        ivBack.shrinkOnClick(::navigateBack)

        rvEpisodesList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = episodesListAdapter
        }
    }

    override fun setupObservers(): EpisodesListViewModel.() -> Unit = {

        obsEpisodes.observe(viewLifecycleOwner) { episodes ->
            episodesListAdapter.submitList(episodes)
            (view?.parent as? ViewGroup)
                ?.doOnPreDraw { startPostponedEnterTransition() }
        }
    }

    private fun handleAnimation() {

        val animDuration: Long = 300
        val animInterpolator = DecelerateInterpolator()

        fun defaultAnimation() {

            ObjectAnimator.ofFloat(binding.ivBack, "alpha", 0f, 0.0f, 1f)
                .apply {
                    duration = animDuration
                    interpolator = animInterpolator
                }.start()

            ObjectAnimator.ofFloat(binding.rvEpisodesList, "alpha", 0f, 0.0f, 1f)
                .apply {
                    duration = animDuration
                    interpolator = animInterpolator
                }.start()

            TranslateAnimation(-100f, 0f, 0f, 0f)
                .apply {
                    duration = animDuration
                    interpolator = animInterpolator
                }.run { binding.ivBack.startAnimation(this) }

            TranslateAnimation(0f, 0f, 200f, 0f)
                .apply {
                    duration = animDuration
                    interpolator = animInterpolator
                }.run { binding.rvEpisodesList.startAnimation(this) }
        }

        fun backAnimation() {

            ObjectAnimator.ofFloat(binding.rvEpisodesList, "alpha", 0f, 0.0f, 1f)
                .apply {
                    duration = animDuration
                    interpolator = animInterpolator
                }.start()

            TranslateAnimation(0f, 0f, -164f, 0f)
                .apply {
                    duration = animDuration
                    interpolator = animInterpolator
                }.run { binding.rvEpisodesList.startAnimation(this) }
        }

        if (hasLanded.compareAndSet(false, true)) defaultAnimation()
        else backAnimation()
    }

    private fun onSeasonClicked(season: EpisodeListData.Season) =
        viewModel.onSeasonClicked(season)

    private fun onEpisodeClicked(episode: EpisodeListData.Episode, name: View) {

        val extras = FragmentNavigatorExtras(name to "episodeName")

        val event = OpenEpisode(
            episodeId = episode.episodeId,
            extras = extras,
        )

        EventBus.getDefault().post(event)
    }

    private fun navigateBack() = EventBus.getDefault().post(NavigateBack)
}