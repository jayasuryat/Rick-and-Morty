package com.jayasuryat.characterlist.domain.repos.definitions

import androidx.paging.PagingData
import com.jayasuryat.characterlist.domain.models.Character
import kotlinx.coroutines.flow.Flow

interface CharacterListRepository {

    fun getPagedCharacters(): Flow<PagingData<Character>>
}