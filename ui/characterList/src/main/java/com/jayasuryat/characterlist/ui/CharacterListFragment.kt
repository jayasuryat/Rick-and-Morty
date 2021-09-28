package com.jayasuryat.characterlist.ui

import androidx.fragment.app.viewModels
import com.jayasuryat.base.arch.BaseAbsFragment
import com.jayasuryat.characterlist.databinding.FragmentCharacterListBinding


class CharacterListFragment : BaseAbsFragment<CharacterListViewModel,
        FragmentCharacterListBinding>(FragmentCharacterListBinding::inflate) {

    override val viewModel: CharacterListViewModel by viewModels()
}