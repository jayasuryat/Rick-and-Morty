package com.jayasuryat.episodelist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.jayasuryat.base.anim.AnimHelper
import com.jayasuryat.base.anim.impl.AlphaAnim
import com.jayasuryat.base.anim.impl.TranslateAnim
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
        savedInstanceState: Bundle?,
    ): View {
        postponeEnterTransition()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun setupViews(): FragmentEpisodesListBinding.() -> Unit = {

        root.post(::handleAnimation)

        ivBack.shrinkOnClick(::navigateBack)

        rvEpisodesList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = episodesListAdapter
        }
    }

    override fun setupObservers(): EpisodesListViewModel.() -> Unit = {

        obsEpisodes.observe(viewLifecycleOwner) { episodes ->
            episodesListAdapter.submitList(episodes)
            (view?.parent as? ViewGroup)?.doOnPreDraw { startPostponedEnterTransition() }
        }
    }

    private fun handleAnimation() {

        fun defaultAnimation() {

            AnimHelper.create {
                addAnim {
                    AlphaAnim.builder()
                        .intermediateSteps(0f)
                        .build(binding.ivBack, binding.rvEpisodesList)
                }
                addAnim {
                    TranslateAnim.builder()
                        .fromHorizontalDelta(-100f)
                        .toCurrentPosition()
                        .build(binding.ivBack)
                }
                addAnim {
                    TranslateAnim.builder()
                        .fromVerticalDelta(164f)
                        .toCurrentPosition()
                        .build(binding.rvEpisodesList)
                }
            }.start()
        }

        fun backAnimation() {

            AnimHelper.create {
                addAnim {
                    AlphaAnim.builder()
                        .intermediateSteps(0f)
                        .build(binding.rvEpisodesList)
                }
                addAnim {
                    TranslateAnim.builder()
                        .fromVerticalDelta(-164f)
                        .toCurrentPosition()
                        .build(binding.rvEpisodesList)
                }
            }.start()
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