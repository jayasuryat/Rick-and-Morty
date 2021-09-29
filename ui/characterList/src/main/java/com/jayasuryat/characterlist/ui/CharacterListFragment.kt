package com.jayasuryat.characterlist.ui

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jayasuryat.base.arch.BaseAbsFragment
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.base.toggleVisibility
import com.jayasuryat.characterlist.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterListFragment : BaseAbsFragment<CharacterListViewModel,
        FragmentCharacterListBinding>(FragmentCharacterListBinding::inflate) {

    private val characterListAdapter: CharactersListAdapter by lazy { CharactersListAdapter() }

    override val viewModel: CharacterListViewModel by viewModels()

    override fun setupViews(): FragmentCharacterListBinding.() -> Unit = {

        ivBack.shrinkOnClick { findNavController().popBackStack() }

        rvCharactersList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterListAdapter
        }
    }

    override fun setupObservers(): CharacterListViewModel.() -> Unit = {

        obsIsDataLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.pbLoading.toggleVisibility(isLoading == true)
        }

        obsCharactersList.observe(viewLifecycleOwner) { characters ->
            characterListAdapter.submitList(characters)
        }
    }
}