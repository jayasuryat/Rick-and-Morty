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

    private val _obsCharactersList: MutableLiveData<List<Character>> = MutableLiveData()
    val obsCharactersList: LiveData<List<Character>> = _obsCharactersList

    init {
        ioScope.launch { loadCharacters() }
    }

    private suspend fun loadCharacters() {

        doWhileLoading {
            val characters = charactersRepository.getCharacters(0).getOrNull()
            _obsCharactersList.postValue(characters)
        }
    }
}