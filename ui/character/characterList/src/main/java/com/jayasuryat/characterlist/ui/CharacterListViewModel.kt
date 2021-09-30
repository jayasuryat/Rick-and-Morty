package com.jayasuryat.characterlist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jayasuryat.base.arch.BaseViewModel
import com.jayasuryat.data.datasources.definitions.CharactersRepository
import com.jayasuryat.data.models.domain.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
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
                imageUrl = character.image,
                false
            )
        }
    }

    internal fun onItemClicked(item: CharacterDef) {
        val data = _obsCharactersList.value
        if (data.isNullOrEmpty()) return
        val mapped = data.map { it.copy(lastSelectedCharacter = item.id == it.id) }
        _obsCharactersList.postValue(mapped)
    }
}