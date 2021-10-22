package com.jayasuryat.characterdetails.presentation.episodes

import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.jayasuryat.base.anim.AnimHelper
import com.jayasuryat.base.anim.impl.AlphaAnim
import com.jayasuryat.base.anim.impl.TranslateAnim
import com.jayasuryat.base.arch.BaseAbsFragment
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.characterdetails.NavigateBack
import com.jayasuryat.characterdetails.OpenEpisode
import com.jayasuryat.characterdetails.databinding.FragmentCharacterEpisodesBinding
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.atomic.AtomicBoolean


@AndroidEntryPoint
class CharacterEpisodesFragment : BaseAbsFragment<CharacterEpisodesViewModel,
        FragmentCharacterEpisodesBinding>(FragmentCharacterEpisodesBinding::inflate) {

    private val hasLanded: AtomicBoolean = AtomicBoolean(false)

    private val episodesAdapter: EpisodeListAdapter by lazy { EpisodeListAdapter(::onEpisodeClicked) }

    override val viewModel: CharacterEpisodesViewModel by viewModels()

    override fun setupViews(): FragmentCharacterEpisodesBinding.() -> Unit = {

        binding.root.post(::animateViews)

        rvEpisodesList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = episodesAdapter
        }

        ivBack.shrinkOnClick(::navigateBack)
    }

    override fun setupObservers(): CharacterEpisodesViewModel.() -> Unit = {

        obsEpisodes.observe(viewLifecycleOwner) { episodes ->
            episodesAdapter.submitList(episodes)
           // (view?.parent as? ViewGroup)?.doOnPreDraw { startPostponedEnterTransition() }
        }
    }

    private fun animateViews() {

        fun enterAnim() {

            val view = nullableBinding?.rvEpisodesList ?: return

            AnimHelper.create {
                addAnim {
                    TranslateAnim.builder()
                        .fromVerticalDelta(200f)
                        .toCurrentPosition()
                        .build(view)
                }
                addAnim {
                    AlphaAnim.builder()
                        .intermediateSteps(0f)
                        .build(view)
                }
            }.start()
        }

        fun backAnim() {

            val view = nullableBinding?.rvEpisodesList ?: return

            AnimHelper.create {
                addAnim {
                    TranslateAnim.builder()
                        .fromVerticalDelta(-200f)
                        .toCurrentPosition()
                        .build(view)
                }
                addAnim {
                    AlphaAnim.builder()
                        .intermediateSteps(0f)
                        .build(view)
                }
            }.start()
        }

        if (hasLanded.compareAndSet(false, true)) enterAnim()
        else backAnim()
    }

    private fun onEpisodeClicked(
        episode: EpisodeData,
        name: View,
        nameContainer: View,
    ) {

        val extras = FragmentNavigatorExtras(
            name to "episodeName",
            nameContainer to "episodeNameContainer"
        )

        val event = OpenEpisode(
            episodeId = episode.episodeId,
            extras = extras,
        )

        EventBus.getDefault().post(event)
    }

    private fun navigateBack() = EventBus.getDefault().post(NavigateBack)
}