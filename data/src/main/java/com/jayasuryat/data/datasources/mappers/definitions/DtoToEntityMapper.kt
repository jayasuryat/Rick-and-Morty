package com.jayasuryat.data.datasources.mappers.definitions

import com.jayasuryat.data.models.DtoModel
import com.jayasuryat.data.models.EntityModel

internal interface DtoToEntityMapper<I : DtoModel, O : EntityModel> : Mapper<I, O>