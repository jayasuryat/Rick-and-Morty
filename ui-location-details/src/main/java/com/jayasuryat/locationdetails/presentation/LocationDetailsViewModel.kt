package com.jayasuryat.locationdetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.jayasuryat.base.arch.BaseViewModel
import com.jayasuryat.locationdetails.domain.models.LocationDetails
import com.jayasuryat.locationdetails.domain.repositories.LocationDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LocationDetailsViewModel @Inject constructor(
    private val locationDetailsRepo: LocationDetailsRepository,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _obsLocation: MutableLiveData<LocationDetails> = MutableLiveData()
    internal val obsLocation: LiveData<LocationDetails> = _obsLocation

    init {
        ioScope.launch { doWhileLoading { loadLocationDetails() } }
    }

    private suspend fun loadLocationDetails() {

        val locationId = savedStateHandle.get<Long>("locationId") ?: return

        val location = locationDetailsRepo.getLocationDetails(locationId)
            .logError()
            .getOrNull() ?: return

        _obsLocation.postValue(location)
    }
}