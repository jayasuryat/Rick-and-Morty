package com.jayasuryat.characterdetails.presentation.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.jayasuryat.base.arch.BaseViewModel
import com.jayasuryat.characterdetails.domain.models.CharacterDetails
import com.jayasuryat.characterdetails.domain.repos.CharacterDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val charactersRepository: CharacterDetailsRepository,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _obsCharacter: MutableLiveData<CharacterDetails> = MutableLiveData()
    val obsCharacter: LiveData<CharacterDetails> = _obsCharacter

    init {
        ioScope.launch { doWhileLoading { loadCharacterDetails() } }
    }

    private suspend fun loadCharacterDetails() {

        val characterId = savedStateHandle.get<Long>("id") ?: 1

        val character = charactersRepository.getCharacterDetails(characterId)
            .logError()
            .getOrNull() ?: return

        _obsCharacter.postValue(character)
    }
}