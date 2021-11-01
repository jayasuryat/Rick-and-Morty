package com.jayasuryat.characterlist.presentation

import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.jayasuryat.base.arch.BaseViewModel
import com.jayasuryat.characterlist.domain.models.Character
import com.jayasuryat.characterlist.domain.repos.definitions.CharacterListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val charactersPagingRepository: CharacterListRepository,
) : BaseViewModel() {

    internal val charactersList: StateFlow<PagingData<CharacterDef>> by lazy {
        charactersPagingRepository.getPagedCharacters()
            .cachedIn(ioScope)
            .map { it.map { character -> character.mapToDef() } }
            .stateIn(ioScope, SharingStarted.WhileSubscribed(), PagingData.empty())
    }

    private fun Character.mapToDef(): CharacterDef = CharacterDef(
        id = id,
        name = name,
        imageUrl = imageUrl,
    )
}