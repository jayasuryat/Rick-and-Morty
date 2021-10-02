package com.jayasuryat.episodelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.jayasuryat.base.arch.BaseAbsFragment
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.episodelist.databinding.FragmentEpisodesListBinding
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus


@AndroidEntryPoint
class EpisodesListFragment : BaseAbsFragment<EpisodesListViewModel,
        FragmentEpisodesListBinding>(FragmentEpisodesListBinding::inflate) {

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
        postponeEnterTransition()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun setupViews(): FragmentEpisodesListBinding.() -> Unit = {

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

    private fun navigateBack() = EventBus.getDefault().post(NavigateBack)

    private fun onSeasonClicked(season: EpisodeListData.Season) =
        viewModel.onSeasonClicked(season)

    private fun onEpisodeClicked(episode: EpisodeListData.Episode) {

    }
}