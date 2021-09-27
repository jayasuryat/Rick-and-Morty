package com.jayasuryat.data.data.local.impl

import androidx.room.Dao
import com.jayasuryat.data.data.local.definitions.CharactersLocalDataSource
import com.jayasuryat.data.data.local.definitions.EpisodesLocalDataSource
import com.jayasuryat.data.data.local.definitions.LocationLocalDataSource

@Dao
internal interface CacheDao :
    CharactersLocalDataSource,
    EpisodesLocalDataSource,
    LocationLocalDataSource