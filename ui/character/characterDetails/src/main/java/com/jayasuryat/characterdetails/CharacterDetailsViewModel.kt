package com.jayasuryat.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.jayasuryat.base.arch.BaseViewModel
import com.jayasuryat.data.datasources.definitions.CharactersRepository
import com.jayasuryat.data.models.domain.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _obsCharacter: MutableLiveData<Character> = MutableLiveData()
    val obsCharacter: LiveData<Character> = _obsCharacter

    init {
        ioScope.launch { doWhileLoading { loadCharacterDetails() } }
    }

    private suspend fun loadCharacterDetails() {

        val characterId = savedStateHandle.get<Long>("id") ?: 1

        charactersRepository
            .getCharacterFromCache(characterId = characterId)
            .getOrNull()
            .let(_obsCharacter::postValue)
    }
}