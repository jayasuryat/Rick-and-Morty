package com.jayasuryat.rickandmorty

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jayasuryat.data.models.KResult
import com.jayasuryat.data.datasources.definitions.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val characterRepo: CharactersRepository,
) : ViewModel() {

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (val result = characterRepo.getCharacters(1)) {
                    is KResult.Error -> {
                        Log.e("GotEm!", result.toString())
                        result.throwable.printStackTrace()
                    }
                    is KResult.Success -> Log.e("GotEm!", result.toString())
                }
            }
        }
    }
}