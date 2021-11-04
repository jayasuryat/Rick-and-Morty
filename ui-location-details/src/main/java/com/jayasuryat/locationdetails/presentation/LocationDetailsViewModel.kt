package com.jayasuryat.locationdetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jayasuryat.base.arch.BaseViewModel
import com.jayasuryat.locationdetails.domain.models.LocationDetails
import com.jayasuryat.locationdetails.domain.repositories.LocationDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LocationDetailsViewModel @Inject constructor(
    private val locationDetailsRepo: LocationDetailsRepository,
) : BaseViewModel() {

    private val _obsLocation: MutableLiveData<LocationDetails> = MutableLiveData()
    internal val obsLocation: LiveData<LocationDetails> = _obsLocation

    fun loadLocationDetails(locationId: Long) {
        ioScope.launch { doWhileLoading { getLocationDetails(locationId = locationId) } }
    }

    private suspend fun getLocationDetails(locationId: Long) {

        val location = locationDetailsRepo.getLocationDetails(locationId)
            .logError()
            .getOrNull() ?: return

        _obsLocation.postValue(location)
    }
}