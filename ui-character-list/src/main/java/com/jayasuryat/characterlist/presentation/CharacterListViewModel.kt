package com.jayasuryat.characterlist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jayasuryat.base.arch.BaseViewModel
import com.jayasuryat.characterlist.domain.models.Character
import com.jayasuryat.characterlist.domain.repos.definitions.CharacterListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val charactersRepository: CharacterListRepository
) : BaseViewModel() {

    private val _obsCharactersList: MutableLiveData<List<CharacterDef>> = MutableLiveData()
    internal val obsCharactersList: LiveData<List<CharacterDef>> = _obsCharactersList

    init {
        ioScope.launch { loadCharacters() }
    }

    private suspend fun loadCharacters() {

        doWhileLoading {

            charactersRepository.getAllCharactersInCache()
                .getOrNull()
                .mapToDef()
                .let(_obsCharactersList::postValue)

            charactersRepository.getCharacters(0)
                .logError()
                .getOrNull()
                .mapToDef()
                .let(_obsCharactersList::postValue)
        }
    }

    private fun List<Character>?.mapToDef(): List<CharacterDef>? {
        return if (this.isNullOrEmpty()) null
        else this.map { character ->
            CharacterDef(
                id = character.id,
                name = character.name,
                imageUrl = character.imageUrl,
            )
        }
    }
}