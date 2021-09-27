package com.jayasuryat.data.datasources.mappers.definitions

import com.jayasuryat.data.models.DomainModel
import com.jayasuryat.data.models.EntityModel

internal fun interface EntityToDomainMapper<I : EntityModel, O : DomainModel> : Mapper<I, O>