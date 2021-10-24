package com.jayasuryat.locationlist.presentation

import androidx.lifecycle.asLiveData
import androidx.paging.cachedIn
import androidx.paging.map
import com.jayasuryat.base.arch.BaseViewModel
import com.jayasuryat.locationlist.domain.model.Location
import com.jayasuryat.locationlist.domain.repository.LocationListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class LocationListViewModel @Inject constructor(
    private val locationsRepository: LocationListRepository,
) : BaseViewModel() {

    internal val obsLocationsList by lazy {
        locationsRepository.getPagedLocations()
            .cachedIn(ioScope)
            .map { it.map { location -> location.mapToDef() } }
            .asLiveData(ioScope.coroutineContext)
    }

    private fun Location.mapToDef(): Location = Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension,
    )
}