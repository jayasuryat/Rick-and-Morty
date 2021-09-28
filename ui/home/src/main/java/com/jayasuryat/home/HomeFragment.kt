package com.jayasuryat.home

import androidx.fragment.app.viewModels
import com.jayasuryat.base.arch.BaseAbsFragment
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.home.databinding.FragmentHomeBinding
import org.greenrobot.eventbus.EventBus


class HomeFragment : BaseAbsFragment<HomeViewModel,
        FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override val viewModel: HomeViewModel by viewModels()

    override fun setupViews(): FragmentHomeBinding.() -> Unit = {

        mcvCharacters.shrinkOnClick { postEvent(OpenCharacters) }
        mcvEpisodes.shrinkOnClick { postEvent(OpenEpisodes) }
        mcvLocations.shrinkOnClick { postEvent(OpenLocations) }
    }

    private fun postEvent(event: HomeScreenEvent) = EventBus.getDefault().post(event)
}