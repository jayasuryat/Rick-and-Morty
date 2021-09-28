package com.jayasuryat.home

import androidx.fragment.app.viewModels
import com.jayasuryat.base.arch.BaseAbsFragment
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.home.databinding.FragmentHomeBinding


class HomeFragment : BaseAbsFragment<HomeViewModel,
        FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override val viewModel: HomeViewModel by viewModels()

    override fun setupViews(): FragmentHomeBinding.() -> Unit = {

        mcvCharacters.shrinkOnClick { }
        mcvEpisodes.shrinkOnClick { }
        mcvLocations.shrinkOnClick { }
    }
}