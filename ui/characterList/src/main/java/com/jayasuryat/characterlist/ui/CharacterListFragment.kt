package com.jayasuryat.characterlist.ui

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.jayasuryat.base.CircleRevealHelper
import com.jayasuryat.base.arch.BaseAbsFragment
import com.jayasuryat.base.show
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.characterlist.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterListFragment : BaseAbsFragment<CharacterListViewModel,
        FragmentCharacterListBinding>(FragmentCharacterListBinding::inflate) {

    private val characterListAdapter: CharactersListAdapter by lazy { CharactersListAdapter() }

    override val viewModel: CharacterListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        postponeEnterTransition()
    }

    override fun setupViews(): FragmentCharacterListBinding.() -> Unit = {

        binding.clRoot.post(::revealRoot)

        ivBack.shrinkOnClick { findNavController().popBackStack() }

        rvCharactersList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterListAdapter
        }
    }

    override fun setupObservers(): CharacterListViewModel.() -> Unit = {

        obsIsDataLoading.observe(viewLifecycleOwner) { isLoading ->
            //binding.pbLoading.toggleVisibility(isLoading == true)
        }

        obsCharactersList.observe(viewLifecycleOwner) { characters ->
            characterListAdapter.submitList(characters)
            binding.rvCharactersList.show()
            startPostponedEnterTransition()
        }
    }

    private fun revealRoot() {

        val startX = arguments?.get("x")?.toString()?.toIntOrNull() ?: 0
        val startY = arguments?.get("y")?.toString()?.toIntOrNull() ?: 0

        CircleRevealHelper.Builder(binding.clRoot)
            .setStartPoint(startX.toDouble(), startY.toDouble())
            .setFarthestPointFromStartAsEnd()
            .build()
            .animation
            .start()
    }
}