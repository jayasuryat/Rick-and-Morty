package com.jayasuryat.characterlist.presentation

import androidx.lifecycle.asLiveData
import androidx.paging.cachedIn
import androidx.paging.map
import com.jayasuryat.base.arch.BaseViewModel
import com.jayasuryat.characterlist.domain.models.Character
import com.jayasuryat.characterlist.domain.repos.definitions.CharacterListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val charactersPagingRepository: CharacterListRepository,
) : BaseViewModel() {

    internal val obsCharactersList by lazy {
        charactersPagingRepository.getPagedCharacters()
            .cachedIn(ioScope)
            .map { it.map { character -> character.mapToDef() } }
            .asLiveData(ioScope.coroutineContext)
    }

    private fun Character.mapToDef(): CharacterDef = CharacterDef(
        id = id,
        name = name,
        imageUrl = imageUrl,
    )
}