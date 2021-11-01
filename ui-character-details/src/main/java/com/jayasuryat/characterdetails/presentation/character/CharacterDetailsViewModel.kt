package com.jayasuryat.characterdetails.presentation.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jayasuryat.base.arch.BaseViewModel
import com.jayasuryat.characterdetails.domain.models.CharacterDetails
import com.jayasuryat.characterdetails.domain.repos.CharacterDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val charactersRepository: CharacterDetailsRepository,
) : BaseViewModel() {

    private val _obsCharacter: MutableLiveData<CharacterDetails> = MutableLiveData()
    internal val obsCharacter: LiveData<CharacterDetails> = _obsCharacter

    fun getCharacterDetails(
        characterId: Long,
    ) {

        ioScope.launch { doWhileLoading { loadCharacterDetails(characterId = characterId) } }
    }

    private suspend fun loadCharacterDetails(
        characterId: Long,
    ) {

        val character = charactersRepository.getCharacterDetails(characterId)
            .logError()
            .getOrNull() ?: return

        _obsCharacter.postValue(character)
    }
}